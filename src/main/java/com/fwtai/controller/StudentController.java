package com.fwtai.controller;

import com.fwtai.entity.Student;
import com.fwtai.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于注解实现 webflux 的方式
 * 使用注解的返回值中Mono的个数 0和或1个;而Flux则可以是0个或N个
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2021/2/7 1:33
 * @QQ号码 444141300
 * @Email service@yinlz.com
 * @官网 <url>http://www.yinlz.com</url>
*/
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // http://127.0.0.1/api/students/findAll
    @GetMapping("/findAll")
    public Flux<Student> findAll(){
        return studentRepository.findAll();
    }

    /**
     * 采用Flux或Mono都能实现响应式或stream流式模型,Flux<T>适用于返回List,使用注解的返回值中Mono的个数 0和或1个;而Flux则可以是0个或N个
     * http://127.0.0.1/api/students/list
     * @param
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2019/6/11 0:23
     */
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Student> getList(){
        final List<Student> list = new ArrayList<Student>();
        for(int i = 0; i < 5; i++){
            final Student student = new Student();
            student.setId(1025429L);
            student.setName("田应平"+i);
            list.add(student);
        }
        return Flux.fromIterable(list).delayElements(Duration.ofSeconds(1));
    }

    // Flux<T>适用于返回List,http://127.0.0.1/api/list
    @GetMapping("/list")
    public Flux<String> list(){
        final ArrayList<String> list = new ArrayList<>();
        list.add("object");
        list.add(",webflux");
        return Flux.fromIterable(list);
    }

    /**
     * 采用Flux或Mono都能实现响应式或stream流式模型,Mono<T>适用于返回单个对象,使用注解的返回值中Mono的个数 0和或1个;而Flux则可以是0个或N个
     * http://127.0.0.1/api/students/user
     * @param
     * @作者 田应平
     * @QQ 444141300
     * @创建时间 2019/6/11 0:23
     */
    @GetMapping("/user")
    public Mono<Student> user(){
        final Student student = new Student();
        student.setId(1000010L);
        student.setName("田卓智");
        return Mono.just(student);
    }

    //http://127.0.0.1/api/students/edit
    @GetMapping("/edit")
    public Mono<Student> edit(){
        final Student student = new Student();
        student.setId(2L);
        student.setName("田卓智");
        student.setCode("1478741");
        Mono<Student> save = studentRepository.save(student);
        return save;
        //return Mono.just(student);
    }

    //http://127.0.0.1/api/students/add
    @GetMapping("/add")
    public Mono<Student> add(){
        final Student student = new Student();
        student.setName("田卓智");
        student.setCode("1478741");
        Mono<Student> save = studentRepository.save(student);
        return save;
        //return Mono.just(student);
    }
}