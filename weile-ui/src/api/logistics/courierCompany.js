import request from '@/utils/request'

// 查询快递公司代码列表
export function listCourierCompany(query) {
    return request({
        url: '/logistics/courierCompany/list',
        method: 'get',
        params: query
    })
}

// 查询快递公司代码详细
export function getCourierCompany(id) {
    return request({
        url: '/logistics/courierCompany/' + id,
        method: 'get'
    })
}

// 新增快递公司代码
export function addCourierCompany(data) {
    return request({
        url: '/logistics/courierCompany',
        method: 'post',
        data: data
    })
}

// 修改快递公司代码
export function updateCourierCompany(data) {
    return request({
        url: '/logistics/courierCompany',
        method: 'put',
        data: data
    })
}

// 删除快递公司代码
export function delCourierCompany(id) {
    return request({
        url: '/logistics/courierCompany/' + id,
        method: 'delete'
    })
}
