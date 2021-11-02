package com.mercadolivre.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Slf4j
public class CucumberDnaFeatures {
//    @ParameterType(name = "dna",value = "(.*)")
//    public List<String> dna(String dna) {
//        log.info(dna);
//        return Arrays.asList(dna.split(",").clone());
//    }

    @Autowired
    @Qualifier("feignClient")
    private CucumberDnaControllerClient client;

    private Boolean returnedIsSimian = false;

    public CucumberDnaFeatures(CucumberDnaControllerClient client) {
        this.client = client;
    }
//    @Given("the client send a sequence {string}")
//    public void the_client_send_a_sequence(String string) {
//        log.info(string.toString());
//        ResponseEntity<DnaCreateResponseDto> response = client.analiseDna(DnaCreateRequestDto.builder()
//                .bases(Arrays.asList(string.split(",").clone()))
//                .build());
//        returnedIsSimian = response.getBody().getIsSimian();
//    }

//    @When("the api response should be (.*)")
//    public void the_api_response_should_be(String simian) {
//        log.info(simian);
//        assertEquals(Boolean.valueOf(simian), returnedIsSimian);
//    }


//    @When("^I put (\\d+) (\\w+) in the bag$")
//    public void i_put_something_in_the_bag(final int quantity, final String something) {
//        IntStream.range(0, quantity)
//                .peek(n -> log.info("Putting a {} in the bag, number {}", something, quantity))
//                .map(ignore -> client.analiseDna(something))
//                .forEach(statusCode -> assertThat(statusCode).isEqualTo(HttpStatus.CREATED.value()));
//    }
//
//    @Then("^the bag should contain only (\\d+) (\\w+)$")
//    public void the_bag_should_contain_only_something(final int quantity, final String something) {
//        assertNumberOfTimes(quantity, something, true);
//    }
//
//    @Then("^the bag should contain (\\d+) (\\w+)$")
//    public void the_bag_should_contain_something(final int quantity, final String something) {
//        assertNumberOfTimes(quantity, something, false);
//    }
//    private void assertNumberOfTimes(final int quantity, final String something, final boolean onlyThat) {
//        final List<String> things = bagHttpClient.getContents().getThings();
//        log.info("Expecting {} times {}. The bag contains {}", quantity, something, things);
//        final int timesInList = Collections.frequency(things, something);
//        assertThat(timesInList).isEqualTo(quantity);
//        if (onlyThat) {
//            assertThat(timesInList).isEqualTo(things.size());
//        }
//    }



}
