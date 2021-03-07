import request from '@/utils/request'
import urls from '@/api/urls'

/**
 * 获取医生日程限制信息
 * @param {doctorId:number} doctorId 医生id
 */
export function getDoctorConstraintById(doctorId) {
  return request({
    url: urls.DoctorConstraint + '/' + doctorId,
    method: 'get',
  })
}

/**
 * 获取医生预约日程表
 * @param {doctorId:number} doctorId 医生id
 */
export function getDoctorCalendarById(doctorId) {
  return request({
    url: urls.DoctorCalendar + '/' + doctorId,
    method: 'get',
  })
}

/**
 * 获取单个预约信息,返回from表格内容，和下面一样的
 * @param {orderId:number} orderId 预约id
 */
export function getOrderById(orderId) {
  return request({
    url: urls.Form + '/' + orderId,
    method: 'get',
  })
}

/**
 * 添加预约
 * 信息存在form中，status置为true
 * @param {*} parmas form表
 */

export function postOrder(params) {
  return request({
    url: urls.Form,
    method: 'post',
    data: params
  })
}

/**
 * 删除预约信息
 * status置为false
 * @param {orderId:number} orderId 预约id
 */
export function deleteOrderById(orderId) {
  return request({
    url: urls.Form + '/' + orderId,
    method: 'put',
  })
}

/**
 * 编辑form
 * @param {id:number} orderId orderId
 * @param {*} params 预约信息form
 */
export function updateOrderById(orderId, params) {
  return request({
    url: urls.Form + '/' + orderId,
    method: 'put',
    data: params
  })
}
