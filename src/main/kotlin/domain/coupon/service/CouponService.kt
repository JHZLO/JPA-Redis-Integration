package org.example.domain.coupon.service

import jakarta.transaction.Transactional
import org.example.domain.coupon.entity.Coupon
import org.example.domain.coupon.repository.CouponRepository
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val couponRepository: CouponRepository
) {

    companion object {
        const val CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        const val COUPON_LENGTH = 10
        const val MAX_COUPON_COUNT = 100
    }

    // 쿠폰 생성하기
    @CachePut(value = ["couponCache"], key = "#result.id")
    @Transactional
    fun createCoupon(): Coupon {
        if (isValidTotalCouponCount()) {
            throw IllegalArgumentException("쿠폰 한도 수량 소진")
        }

        var couponValue: String
        do {
            couponValue = generateCouponValue()
        } while (isDuplicatedCoupon(couponValue))

        val coupon = Coupon(value = couponValue)
        return couponRepository.save(coupon)
    }

    private fun generateCouponValue(): String {
        return (1..COUPON_LENGTH)
            .map { CHARSET.random() }
            .joinToString("")
    }

    private fun isDuplicatedCoupon(couponValue: String): Boolean {
        return couponRepository.existsByValue(couponValue)
    }

    private fun isValidTotalCouponCount(): Boolean {
        return getCouponTotalCount() >= MAX_COUPON_COUNT
    }

    private fun getCouponTotalCount(): Long {
        return couponRepository.count()
    }

    // 쿠폰 id로 조회하기
    @Cacheable(value = ["couponCache"], key = "#id")
    fun findById(id: Long): Coupon{
        return couponRepository.findById(id)
            .orElseThrow{NoSuchElementException("쿠폰 id 조회 안됨")}
    }
}
