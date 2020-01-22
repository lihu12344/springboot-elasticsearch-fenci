package com.example.demo.controller;

import com.example.demo.dao.PeopleRepository;
import com.example.demo.pojo.People;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class PeopleController {

    @Resource
    private PeopleRepository peopleRepository;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @PostConstruct
    public void start(){
        elasticsearchRestTemplate.putMapping(People.class);
    }

    @RequestMapping("/save")
    public String save(){
        People people=new People();
        for (int i=0;i<10;i++){
            people.setId(i);
            people.setName("瓜田李下 "+i);
            people.setDescription("床前明月光"+i);
            people.setDescription2("疑似地上霜"+i);
            people.setDescription3("举头望明月"+i);
            people.setDescription4("低头思故乡"+i);
            people.setDescription5("欲穷千里目，更上一层楼"+i);

            peopleRepository.save(people);
        }

        return "success";
    }

    @RequestMapping("/get")
    public List<People> get_IK(@RequestParam("description") String description){
        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("description",description))
                .build();

        List<People> list=peopleRepository.search(nativeSearchQuery).getContent();
        if (list.size()!=0){
            list.forEach(System.out::println);
        }

        return list;
    }

    @RequestMapping("/get2")
    public List<People> get_hanlp(@RequestParam("description") String description){
        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("description2",description))
                .build();

        List<People> list=peopleRepository.search(nativeSearchQuery).getContent();
        if (list.size()!=0){
            list.forEach(System.out::println);
        }

        return list;
    }

    @RequestMapping("/get3")
    public List<People> get_pinyin(@RequestParam("description") String description){
        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("description3",description))
                .build();

        List<People> list=peopleRepository.search(nativeSearchQuery).getContent();
        if (list.size()!=0){
            list.forEach(System.out::println);
        }

        return list;
    }

    @RequestMapping("/get4")
    public  List<People> get_IK_pinyin(@RequestParam("description") String description){
        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(description,"description4","description4.inner"))
                .build();

        List<People> list=peopleRepository.search(nativeSearchQuery).getContent();
        if (list.size()!=0){
            list.forEach(System.out::println);
        }

        return list;
    }

    @RequestMapping("/get5")
    public List<People> get_hanlp_pinyin(@RequestParam("description") String description){
        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery(description,"description5","description5.inner"))
                .build();

        List<People> list=peopleRepository.search(nativeSearchQuery).getContent();
        if (list.size()!=0){
            list.forEach(System.out::println);
        }

        return list;
    }
}
