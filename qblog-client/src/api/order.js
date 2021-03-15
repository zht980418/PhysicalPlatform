import instance from '@/http/request'
import urls from "@/http/urls";

export {
    getConstraintById, getCalendarById, postOrder, getOrderById, deleteOrderById, updateOrderById
}

/**
 * 获取医生预约限制条件
 * @param {doctorId:number} doctorId 医生id
 */
function getConstraintById(doctorId) {
    return instance.get(urls.doctordate + '/' + doctorId).then(res => res.data)
}

/**
 * 获取医生预约日程表
 * 返回 orderid title【name】 start end uid
 * @param {doctorId:number} doctorId 医生id
 */
function getCalendarById(doctorId) {
    return instance.get(urls.doctorCalendar + '/' + doctorId).then(res => res.data)
}

/**
 * 添加预约
 * @param {*} form 预约信息
 */
function postOrder(form) {
    console.log(form)
    return instance.post(urls.order, form).then(res => res.data)
}

/**
 * 获取预约信息【form】
 * @param {orderId:number} orderId 预约id
 */
function getOrderById(orderId) {
    return instance.get(urls.order + '/' + orderId).then(res => res.data)
}

/**
 * 删除预约信息【form】
 * @param {orderId:number} orderId 预约id
 */
function deleteOrderById(orderId) {
    return instance.delete(urls.order + '/' + orderId).then(res => res.data)
}

/**
 * 修改预约信息
 * @param {orderId:number} orderId 预约id
 */
function updateOrderById(orderId, form) {
    return instance.put(urls.order + '/' + orderId, form).then(res => res.data)
}