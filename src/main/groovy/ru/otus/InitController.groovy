package ru.otus

import org.camunda.bpm.engine.RuntimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/tasks")
class InitController {


    @Autowired RuntimeService runtimeService

    @GetMapping("/start")
    def startProcess(){
        Map<String, Object> variables = new HashMap<String, Object>();
//        variables.put("inputArray", new Integer[]{5, 23, 42});
        runtimeService.startProcessInstanceByKey("process_1");
    }

}
