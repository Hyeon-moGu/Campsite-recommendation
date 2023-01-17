package com.example.project.location.cache;

import com.example.project.location.dto.LocationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationRedisTemplateService {

    private static final String CACHE_KEY = "LOCATION";

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    private HashOperations<String, String, String> hashOperations;

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(LocationDto locationDto) {
        if(Objects.isNull(locationDto) || Objects.isNull(locationDto.getId())) {
            log.error("Required Values must not be null");
            return;
        }

        try {
            hashOperations.put(CACHE_KEY,
                    locationDto.getId().toString(),
                    serializeLocationDto(locationDto));
            log.info("[LocationRedisTemplateService save success] id: {}", locationDto.getId());
        } catch (Exception e) {
            log.error("[LocationRedisTemplateService save error] {}", e.getMessage());
        }
    }

    public List<LocationDto> findAll() {

        try {
            List<LocationDto> list = new ArrayList<>();
            for (String value : hashOperations.entries(CACHE_KEY).values()) {
                LocationDto locationDto = deserializeLocationDto(value);
                list.add(locationDto);
            }
            return list;

        } catch (Exception e) {
            log.error("[LocationRedisTemplateService findAll error]: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public void delete(Long id) {
        hashOperations.delete(CACHE_KEY, String.valueOf(id));
        log.info("[LocationRedisTemplateService delete]: {} ", id);
    }

    private String serializeLocationDto(LocationDto locationDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(locationDto);
    }

    private LocationDto deserializeLocationDto(String value) throws JsonProcessingException {
        return objectMapper.readValue(value, LocationDto.class);
    }

}
