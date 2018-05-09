package cn.jambin.service.impl;

import cn.jambin.base.BaseServiceImpl;
import cn.jambin.base.annotation.BaseService;
import cn.jambin.entity.Rating;
import cn.jambin.entity.RatingExample;
import cn.jambin.mapper.RatingMapper;
import cn.jambin.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@BaseService
public class RatingServiceimpl extends BaseServiceImpl<RatingMapper, Rating, RatingExample> implements RatingService {


}
