package cn.jambin.service.impl;

import cn.jambin.base.BaseServiceImpl;
import cn.jambin.base.annotation.BaseService;
import cn.jambin.entity.Logs;
import cn.jambin.entity.LogsExample;
import cn.jambin.mapper.LogsMapper;
import cn.jambin.service.LogsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@BaseService
public class LogsServiceimpl extends BaseServiceImpl<LogsMapper, Logs, LogsExample> implements LogsService {


}
