/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.yan2b.web.mapstruct;

import com.yan2b.core.base.BaseMapper;
import com.yan2b.core.model.entity.sys.SysOperationLog;
import com.yan2b.web.model.vo.SysOperationLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author ZhouBing
 * @date 2019-5-22
 * @descripte 测试SysOperationLog类型转换
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SysOperatorLogTranslateMapper extends BaseMapper<SysOperationLogVO, SysOperationLog> {

}
