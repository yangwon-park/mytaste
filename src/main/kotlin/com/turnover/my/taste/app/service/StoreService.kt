package com.turnover.my.taste.app.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(transactionManager = "appTransactionManager", readOnly = true)
class StoreService {


}