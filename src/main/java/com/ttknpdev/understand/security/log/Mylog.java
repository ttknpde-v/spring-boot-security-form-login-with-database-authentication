package com.ttknpdev.understand.security.log;

import com.ttknpdev.understand.security.services.CustomUserDetail;
import org.apache.log4j.Logger;

public interface Mylog {
    Logger customUserDetailLog = Logger.getLogger(CustomUserDetail.class);
}
