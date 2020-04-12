package cmd.lucas.feedpoll.controller;

import cmd.lucas.feedpoll.util.Mappings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebApiController {

    @GetMapping(Mappings.FEED)
    public TestResult feed() {
        return new TestResult(2);
    }

    private static class TestResult {
        private int age;

        public TestResult(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
