package com.mercadolivre.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolivre.dna.dto.DnaCreateRequestDto;
import io.cucumber.java.en.Given;
import io.cucumber.messages.internal.com.google.common.base.Splitter;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.http.HttpStatus;
@Slf4j
public class CucumberDnaFeatures {

    @Given("^the client send a sequence (\\w+)$")
    public void the_client_send_a_sequence(String dna) throws JsonProcessingException {
        log.info(dna);
        List<String> bases = Splitter
                .fixedLength(6)
                .splitToList(dna);

        String request = new ObjectMapper().writeValueAsString(DnaCreateRequestDto.builder()
                .bases(bases)
                .build());
        IntStream.range(0, 1)
                .peek(n -> log.info("Posting sequence {}" , dna))
                .map(ignore -> new HttpClient().post(request))
                .forEach(statusCode -> assertEquals(statusCode, HttpStatus.OK.value()));

    }

    @Given("the client send a payload")
    public void theClientSendAPayload(String payload) {
        IntStream.range(0, 1)
                .peek(n -> log.info("Posting sequence {}" , payload))
                .map(ignore -> new HttpClient().post(payload))
                .forEach(statusCode -> assertEquals(statusCode, HttpStatus.OK.value()));
    }


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
