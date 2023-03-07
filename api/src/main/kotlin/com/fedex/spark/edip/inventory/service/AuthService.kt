package com.fedex.spark.edip.inventory.service

import com.fedex.spark.edip.inventory.model.Organization
import com.fedex.spark.edip.inventory.model.SubOrganization
import com.shoprunner.cosmos.flatMapN
import mu.KotlinLogging

class AuthInfo private constructor(val org: Organization, val subOrg: SubOrganization) {

    companion object {
        fun create(token: String): Result<AuthInfo> {
            val splits = token.split(':', limit = 2)
            return if (splits.size == 2)
                Result.flatMapN(Organization.create(splits[0]), SubOrganization.create(splits[1])) { org, subOrg ->
                    Result.success(AuthInfo(org, subOrg))
                } else {
                Result.failure(Throwable("can't split token $token into org/suborg"))
            }
        }
    }
}

object AuthService {
    private val logger = KotlinLogging.logger {}

    fun authenticate(token: String): AuthInfo? {
        val res = AuthInfo.create(token)
        if (res.isFailure) {
            logger.warn { "Authentication failure for '$token'" }
        }
        return res.getOrNull()
    }
}