import request from '@/utils/request'

// 查询重大阳性项目列表
export function listItem(query) {
  return request({
    url: '/health/item/list',
    method: 'get',
    params: query
  })
}

// 查询重大阳性项目详细
export function getItem(healthItemId) {
  return request({
    url: '/health/item/' + healthItemId,
    method: 'get'
  })
}

// 新增重大阳性项目
export function addItem(data) {
  return request({
    url: '/health/item',
    method: 'post',
    data: data
  })
}

// 修改重大阳性项目
export function updateItem(data) {
  return request({
    url: '/health/item',
    method: 'put',
    data: data
  })
}

// 删除重大阳性项目
export function delItem(healthItemId) {
  return request({
    url: '/health/item/' + healthItemId,
    method: 'delete'
  })
}

// 导出重大阳性项目
export function exportItem(query) {
  return request({
    url: '/health/item/export',
    method: 'get',
    params: query
  })
}