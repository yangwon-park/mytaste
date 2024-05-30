package com.turnover.my.taste.app.service.login

import com.turnover.my.taste.app.domain.login.dto.LoginDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true, rollbackFor = [Exception::class])
class LoginService {


    fun proceedSnsLogin(
        snsInfo: LoginDTO.SnsInfo,
        deviceInfo: String?,
    ) {
        val tokenSubject = "${snsInfo.snsId}$DELIMITER${snsInfo.jointRoute}"




        TODO("Not yet implemented")


    }

    companion object {
        private const val DELIMITER = "."
    }
}