package com.example.demo.dao;

import com.example.demo.pojo.People;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PeopleRepository extends ElasticsearchRepository<People,Integer> {
}
