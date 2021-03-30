import request from '@/utils/request'

// 查询历年体检数据列表
export function listPcount(query) {
  return request({
    url: '/ecg/pcount/list',
    method: 'get',
    params: query
  })
}

// 查询历年体检数据详细
export function getPcount(yearCountId) {
  return request({
    url: '/ecg/pcount/' + yearCountId,
    method: 'get'
  })
}

// 新增历年体检数据
export function addPcount(data) {
  return request({
    url: '/ecg/pcount',
    method: 'post',
    data: data
  })
}

// 修改历年体检数据
export function updatePcount(data) {
  return request({
    url: '/ecg/pcount',
    method: 'put',
    data: data
  })
}

// 删除历年体检数据
export function delPcount(yearCountId) {
  return request({
    url: '/ecg/pcount/' + yearCountId,
    method: 'delete'
  })
}

// 导出历年体检数据
export function exportPcount(query) {
  return request({
    url: '/ecg/pcount/export',
    method: 'get',
    params: query
  })
}
