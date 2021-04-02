import request from '@/utils/request'

// 查询重大阳性信息列表
export function listInfo(query) {
  return request({
    url: '/health/info/list',
    method: 'get',
    params: query
  })
}

// 查询重大阳性信息详细
export function getInfo(infoId) {
  return request({
    url: '/health/info/' + infoId,
    method: 'get'
  })
}

// 新增重大阳性信息
export function addInfo(data) {
  return request({
    url: '/health/info',
    method: 'post',
    data: data
  })
}

// 修改重大阳性信息
export function updateInfo(data) {
  return request({
    url: '/health/info',
    method: 'put',
    data: data
  })
}

// 删除重大阳性信息
export function delInfo(infoId) {
  return request({
    url: '/health/info/' + infoId,
    method: 'delete'
  })
}

// 导出重大阳性信息
export function exportInfo(query) {
  return request({
    url: '/health/info/export',
    method: 'get',
    params: query
  })
}
