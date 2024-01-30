package com.turnover.my.taste.app.service.menu

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class MenuService {

}