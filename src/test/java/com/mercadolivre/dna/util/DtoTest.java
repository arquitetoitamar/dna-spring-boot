package com.mercadolivre.dna.util;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Supplier;
/**
 * A utility class which allows for testing entity and transfer object classes. This is mainly for code coverage since
 * these types of objects are normally nothing more than getters and setters. If any logic exists in the method, then
 * the get method name should be sent in as an ignored field and a custom test function should be written.
 *
 * @param <T> The object type to test.
 */
public abstract class DtoTest<T> {

    /** A map of default mappers for common objects. */
    private static final ImmutableMap<Class<?>, Supplier<?>> DEFAULT_MAPPERS;

    static {
        final Builder<Class<?>, Supplier<?>> mapperBuilder = ImmutableMap.builder();

        /* Primitives */
        mapperBuilder.put(int.class, () -> 0);
        mapperBuilder.put(double.class, () -> 0.0d);
        mapperBuilder.put(float.class, () -> 0.0f);
        mapperBuilder.put(long.class, () -> 0l);
        mapperBuilder.put(boolean.class, () -> true);
        mapperBuilder.put(short.class, () -> (short) 0);
        mapperBuilder.put(byte.class, () -> (byte) 0);
        mapperBuilder.put(char.class, () -> (char) 0);

        mapperBuilder.put(Integer.class, () -> Integer.valueOf(0));
        mapperBuilder.put(Double.class, () -> Double.valueOf(0.0));
        mapperBuilder.put(Float.class, () -> Float.valueOf(0.0f));
        mapperBuilder.put(Long.class, () -> Long.valueOf(0));
        mapperBuilder.put(Boolean.class, () -> Boolean.TRUE);
        mapperBuilder.put(Short.class, () -> Short.valueOf((short) 0));
        mapperBuilder.put(Byte.class, () -> Byte.valueOf((byte) 0));
        mapperBuilder.put(Character.class, () -> Character.valueOf((char) 0));

        mapperBuilder.put(BigDecimal.class, () -> BigDecimal.ONE);
        mapperBuilder.put(Date.class, () -> new Date());

        /* Collection Types. */
        mapperBuilder.put(Set.class, () -> Collections.emptySet());
        mapperBuilder.put(SortedSet.class, () -> Collections.emptySortedSet());
        mapperBuilder.put(List.class, () -> Collections.emptyList());
        mapperBuilder.put(Map.class, () -> Collections.emptyMap());
        mapperBuilder.put(SortedMap.class, () -> Collections.emptySortedMap());

        DEFAULT_MAPPERS = mapperBuilder.build();
    }

    /** The get fields to ignore and not try to test. */
    private final Set<String> ignoredGetFields;

    /**
     * A custom mapper. Normally used when the test class has abstract objects.
     */
    private final ImmutableMap<Class<?>, Supplier<?>> mappers;
    public DtoTest(){
        this.ignoredGetFields = new HashSet<>();
        this.mappers = DEFAULT_MAPPERS;
        this.ignoredGetFields.add("getClass");
    }
    /**
     * Creates an instance of {@link DtoTest} with the default ignore fields.
     *
     * @param customMappers Any custom mappers for a given class type.
     * @param ignoreFields The getters which should be ignored (e.g., "getId" or "isActive").
     */
    public DtoTest(Map<Class<?>, Supplier<?>> customMappers, Set<String> ignoreFields) {
        this.ignoredGetFields = new HashSet<>();
        if (ignoreFields != null) {
            this.ignoredGetFields.addAll(ignoreFields);
        }
        this.ignoredGetFields.add("getClass");

        if (customMappers == null) {
            this.mappers = DEFAULT_MAPPERS;
        } else {
            final Builder<Class<?>, Supplier<?>> builder = ImmutableMap.builder();
            builder.putAll(customMappers);
            builder.putAll(DEFAULT_MAPPERS);
            this.mappers = builder.build();
        }
    }

    /**
     * Creates an object for the given {@link Class}.
     *
     * @param fieldName The name of the field.
     * @param clazz The {@link Class} type to create.
     *
     * @return A new instance for the given {@link Class}.
     *
     * @throws InstantiationException If this Class represents an abstract class, an interface, an array class, a
     *             primitive type, or void; or if the class has no nullary constructor; or if the instantiation fails
     *             for some other reason.
     * @throws IllegalAccessException If the class or its nullary constructor is not accessible.
     *
     */
    private Object createObject(String fieldName, Class<?> clazz)
            throws InstantiationException, IllegalAccessException {

        final Supplier<?> supplier = this.mappers.get(clazz);
        if (supplier != null) {
            return supplier.get();
        }

        if (clazz.isEnum()) {
            return clazz.getEnumConstants()[0];
        }

        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Unable to create objects for field '" + fieldName + "'.", e);
        }
    }

    /**
     * Returns an instance to use to test the get and set methods.
     *
     * @return An instance to use to test the get and set methods.
     */
    public abstract T getInstance();
    /**
     * Tests all the getters and setters. Verifies that when a set method is called, that the get method returns the
     * same thing. This will also use reflection to set the field if no setter exists (mainly used for user immutable
     * entities but Hibernate normally populates).
     *
     * @throws Exception If an unexpected error occurs.
     */
    @Test
    public void testGettersAndSetters() throws Exception {
        /* Sort items for consistent test runs. */
        final SortedMap<String, GetterSetterPair> getterSetterMapping = new TreeMap<>();

        final T instance = getInstance();

        for (final Method method : instance.getClass().getMethods()) {
            final String methodName = method.getName();

            if (this.ignoredGetFields.contains(methodName)) {
                continue;
            }

            String objectName;
            if (methodName.startsWith("get") && method.getParameters().length == 0) {
                /* Found the get method. */
                objectName = methodName.substring("get".length());

                GetterSetterPair getterSettingPair = getterSetterMapping.get(objectName);
                if (getterSettingPair == null) {
                    getterSettingPair = new GetterSetterPair();
                    getterSetterMapping.put(objectName, getterSettingPair);
                }
                getterSettingPair.setGetter(method);
            } else if (methodName.startsWith("set") && method.getParameters().length == 1) {
                /* Found the set method. */
                objectName = methodName.substring("set".length());

                GetterSetterPair getterSettingPair = getterSetterMapping.get(objectName);
                if (getterSettingPair == null) {
                    getterSettingPair = new GetterSetterPair();
                    getterSetterMapping.put(objectName, getterSettingPair);
                }
                getterSettingPair.setSetter(method);
            } else if (methodName.startsWith("is") && method.getParameters().length == 0) {
                /* Found the is method, which really is a get method. */
                objectName = methodName.substring("is".length());

                GetterSetterPair getterSettingPair = getterSetterMapping.get(objectName);
                if (getterSettingPair == null) {
                    getterSettingPair = new GetterSetterPair();
                    getterSetterMapping.put(objectName, getterSettingPair);
                }
                getterSettingPair.setGetter(method);
            }
        }

        /*
         * Found all our mappings. Now call the getter and setter or set the field via reflection and call the getter
         * it doesn't have a setter.
         */
        for (final Entry<String, GetterSetterPair> entry : getterSetterMapping.entrySet()) {
            final GetterSetterPair pair = entry.getValue();

            final String objectName = entry.getKey();
            final String fieldName = objectName.substring(0, 1).toLowerCase() + objectName.substring(1);

            if (pair.hasGetterAndSetter()) {
                /* Create an object. */
                final Class<?> parameterType = pair.getSetter().getParameterTypes()[0];
                final Object newObject = createObject(fieldName, parameterType);

                pair.getSetter().invoke(instance, newObject);

                callGetter(fieldName, pair.getGetter(), instance, newObject);
            } else if (pair.getGetter() != null) {
                /*
                 * Object is immutable (no setter but Hibernate or something else sets it via reflection). Use
                 * reflection to set object and verify that same object is returned when calling the getter.
                 */
                final Object newObject = createObject(fieldName, pair.getGetter().getReturnType());
                final Field field = instance.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(instance, newObject);

                callGetter(fieldName, pair.getGetter(), instance, newObject);
            }
        }
    }

    /**
     * Calls a getter and verifies the result is what is expected.
     *
     * @param fieldName The field name (used for error messages).
     * @param getter The get {@link Method}.
     * @param instance The test instance.
     * @param fieldType The type of the return type.
     * @param expected The expected result.
     *
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private void callGetter(String fieldName, Method getter, T instance, Object expected)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        final Object getResult = getter.invoke(instance);

        if (getter.getReturnType().isPrimitive()) {
            /* Calling assetEquals() here due to autoboxing of primitive to object type. */
            Assert.assertEquals(fieldName + " is different", expected, getResult);
        } else {
            /* This is a normal object. The object passed in should be the exactly same object we get back. */
            Assert.assertSame(fieldName + " is different", expected, getResult);
        }
    }

}