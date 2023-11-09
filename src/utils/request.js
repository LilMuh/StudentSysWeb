import axios from 'axios'

// create axios object
const request = axios.create({
    baseURL: 'http://localhost:8080/tomH_Page',
    timeout: 5000
})


// // request (拦截器) 再发送请求前可以对请求做一些处理，比如加token或者加密
request.interceptors.request.use(config => {
    config.headers['Content-Type']= 'application/json;charset=utf-8';

    // 设置请求头 config.headers['token'] = user.token;
    return config;
}, error => {
    return Promise.reject(error)
});

// response (拦截器) 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        // response.data 就是后端返回的result
        let res = response.data;
        // get the String from json
        if(typeof res === 'string'){res = res ? JSON.parse(res): res}
        return res;
    },
    error => {
        console.log('err'+error) // for debug 给前端看
        return Promise.reject(error)
    }
)

// 导出 request 供后续应用
export default request

