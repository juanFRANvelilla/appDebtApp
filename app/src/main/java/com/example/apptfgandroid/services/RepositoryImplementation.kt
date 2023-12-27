//package com.example.tfgapp.services
//
//import com.example.tfgapp.models.LoginResponse
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import java.io.IOException
//import retrofit2.HttpException
//
//class RepositoryImplementation (
//    private val apiService:ApiService
//): Repository{
//    override suspend fun login(): Flow<Result<LoginResponse>> {
//        return flow{
//            val response= try{
//                apiService.login()
//            } catch (e: IOException){
//                e.printStackTrace()
//                emit(Result.Error(message = "Error en el login"))
//                return@flow
//            } catch( e: HttpException){
//                e.printStackTrace()
//                emit(Result.Error(message = "Error en el login"))
//                return@flow
//            }
//            emit(Result.Success(response))
//        }
//    }
//}