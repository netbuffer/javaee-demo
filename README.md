# javaee-demo
![](https://img.shields.io/static/v1?label=java&message=1.8&color=blue)
![](https://img.shields.io/static/v1?label=javaee&message=7.0&color=blue)
![](https://img.shields.io/static/v1?label=cors-filter&message=2.9&color=blue)
![](https://img.shields.io/static/v1?label=fastjson&message=1.2.78&color=blue)
![](https://img.shields.io/static/v1?label=junit&message=4.13.2&color=black)
> 测试javaee相关技术  
[github源](https://github.com/netbuffer/javaee-demo)  
[gitee源](https://gitee.com/netbuffer/javaee-demo)

* /TestServlet 测试javaee监听器
* [cors-filter跨域设置](http://software.dzhuvinov.com/cors-filter-installation.html)
* 通过filter修改http response `ModifyHttpResponseFilter`

## Docker Image
* https://hub.docker.com/r/javawiki/javaee-demo
* docker build -t javawiki/javaee-demo:1.0.0 . --no-cache
* docker push javawiki/javaee-demo:1.0.0
* docker run -it -p 8080:8080 --name javaee-demo -h javaee-demo  --rm javawiki/javaee-demo:1.0.0
* curl http://localhost:8080/javaee-demo/