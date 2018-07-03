demoApp.factory('HttpInterceptor', ['$q', HttpInterceptor]);

function HttpInterceptor($q) {
    return {
        request: function (config) {
            return config;
        },
        requestError: function (err) {
            return $q.reject(err);
        },
        response: function (res) {
            if(res.config.url.indexOf("service/")>=0 ){
                if(!res.data.success  ){
                    if(res.data.message== "isNotLogin"){
                        window.location.href="/login.html";
                    }else{
                        window.location.href="/500.html";
                    }

                }
                return res;
            }else{
                return res;
            }

        },
        responseError: function (err) {
            if (-1 === err.status) {
                // 远程服务器无响应
            } else if (500 === err.status) {
                // 处理各类自定义错误
            } else if (501 === err.status) {
                // ...
            }
            return $q.reject(err);
        }
    };
}