package com.yan2b.web.mapstruct;

import com.yan2b.core.model.entity.sys.SysOperationLog;
import com.yan2b.web.model.vo.SysOperationLogVO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-30T16:40:31+0800",
    comments = "version: 1.5.0.Beta1, compiler: javac, environment: Java 1.8.0_282 (Azul Systems, Inc.)"
)
@Component
public class SysOperatorLogTranslateMapperImpl implements SysOperatorLogTranslateMapper {

    @Override
    public SysOperationLog toEntity(SysOperationLogVO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysOperationLog sysOperationLog = new SysOperationLog();

        sysOperationLog.setId( arg0.getId() );
        sysOperationLog.setTitle( arg0.getTitle() );
        sysOperationLog.setBusinessType( arg0.getBusinessType() );
        Integer[] businessTypes = arg0.getBusinessTypes();
        if ( businessTypes != null ) {
            sysOperationLog.setBusinessTypes( Arrays.copyOf( businessTypes, businessTypes.length ) );
        }
        sysOperationLog.setClassMethod( arg0.getClassMethod() );
        sysOperationLog.setRequestMethod( arg0.getRequestMethod() );
        sysOperationLog.setOperatorType( arg0.getOperatorType() );
        sysOperationLog.setOperationName( arg0.getOperationName() );
        sysOperationLog.setOperationUrl( arg0.getOperationUrl() );
        sysOperationLog.setOperationIp( arg0.getOperationIp() );
        sysOperationLog.setOperationLocation( arg0.getOperationLocation() );
        sysOperationLog.setOperationParam( arg0.getOperationParam() );
        sysOperationLog.setJsonResult( arg0.getJsonResult() );
        sysOperationLog.setOperationStatus( arg0.getOperationStatus() );
        sysOperationLog.setErrorMsg( arg0.getErrorMsg() );
        sysOperationLog.setOperationTime( arg0.getOperationTime() );

        return sysOperationLog;
    }

    @Override
    public SysOperationLogVO toVo(SysOperationLog arg0) {
        if ( arg0 == null ) {
            return null;
        }

        SysOperationLogVO sysOperationLogVO = new SysOperationLogVO();

        sysOperationLogVO.setId( arg0.getId() );
        sysOperationLogVO.setTitle( arg0.getTitle() );
        sysOperationLogVO.setBusinessType( arg0.getBusinessType() );
        Integer[] businessTypes = arg0.getBusinessTypes();
        if ( businessTypes != null ) {
            sysOperationLogVO.setBusinessTypes( Arrays.copyOf( businessTypes, businessTypes.length ) );
        }
        sysOperationLogVO.setClassMethod( arg0.getClassMethod() );
        sysOperationLogVO.setRequestMethod( arg0.getRequestMethod() );
        sysOperationLogVO.setOperatorType( arg0.getOperatorType() );
        sysOperationLogVO.setOperationName( arg0.getOperationName() );
        sysOperationLogVO.setOperationUrl( arg0.getOperationUrl() );
        sysOperationLogVO.setOperationIp( arg0.getOperationIp() );
        sysOperationLogVO.setOperationLocation( arg0.getOperationLocation() );
        sysOperationLogVO.setOperationParam( arg0.getOperationParam() );
        sysOperationLogVO.setJsonResult( arg0.getJsonResult() );
        sysOperationLogVO.setOperationStatus( arg0.getOperationStatus() );
        sysOperationLogVO.setErrorMsg( arg0.getErrorMsg() );
        sysOperationLogVO.setOperationTime( arg0.getOperationTime() );

        return sysOperationLogVO;
    }

    @Override
    public List<SysOperationLog> toEntity(List<SysOperationLogVO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SysOperationLog> list = new ArrayList<SysOperationLog>( arg0.size() );
        for ( SysOperationLogVO sysOperationLogVO : arg0 ) {
            list.add( toEntity( sysOperationLogVO ) );
        }

        return list;
    }

    @Override
    public List<SysOperationLogVO> toVo(List<SysOperationLog> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<SysOperationLogVO> list = new ArrayList<SysOperationLogVO>( arg0.size() );
        for ( SysOperationLog sysOperationLog : arg0 ) {
            list.add( toVo( sysOperationLog ) );
        }

        return list;
    }
}
