package com.usher.rpc.cluster.router;

import com.usher.rpc.common.ServiceURL;

public interface ServiceRouteStrategy {
    ServiceURL chooseServiceUrl();
}
