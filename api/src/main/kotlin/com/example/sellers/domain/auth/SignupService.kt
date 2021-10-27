package com.example.sellers.domain.auth

import com.example.sellers.SellersException
import com.example.sellers.domain.user.User
import com.example.sellers.domain.user.UserRepository
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SignupService @Autowired constructor(private val userRepository: UserRepository) {
    fun signup(signUpRequest: SignUpRequest){
        validateRequest(signUpRequest)
        checkDuplicates(signUpRequest.email)
        registerUser(signUpRequest)
    }
    private fun validateRequest(signUpRequest: SignUpRequest){
        validateEmail(signUpRequest.email)
        validateName(signUpRequest.name)
        validatePassword(signUpRequest.password)
    }
    private fun validateEmail(email: String){
        val isNotValidEmail = "^[A-Z0-9._%+]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
            .toRegex(RegexOption.IGNORE_CASE)
            .matches(email)
            .not()
        if(isNotValidEmail){
            throw SellersException("이메일 형식이 올바르지 않습니다.")
        }
    }
    private fun validateName(name: String){
        if (name.trim().length !in 2..20)
            throw SellersException("이름은 2자 이상 20자 이하여야 합니다.")
    }
    private fun validatePassword(password: String){
        if(password.trim().length !in 8..20)
            throw SellersException("비밀번호는 공백을 제외하고 8자 이상 20자 이하여야 합니다.")
    }

    private fun checkDuplicates(email: String) =
        userRepository.findByEmail(email)?.let {
            throw SellersException("이미 사용 중인 이메일입니다.")
        }

    private fun registerUser(signUpRequest: SignUpRequest){
        with(signUpRequest){
            val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
            val user = User(email, hashedPassword, name)
            userRepository.save(user)
        }
    }
}