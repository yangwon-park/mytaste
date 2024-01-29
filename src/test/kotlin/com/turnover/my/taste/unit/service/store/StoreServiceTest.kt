package com.turnover.my.taste.unit.service.store

import com.turnover.my.taste.app.domain.embedded.Address
import com.turnover.my.taste.app.domain.embedded.BusinessTime
import com.turnover.my.taste.app.domain.store.enums.ParkStatus
import com.turnover.my.taste.app.domain.store.Store
import com.turnover.my.taste.app.domain.store.enums.StoreStatus
import com.turnover.my.taste.app.domain.store.dto.StoreDTO
import com.turnover.my.taste.app.repository.store.StoreCustomRepository
import com.turnover.my.taste.app.repository.store.StoreRepository
import com.turnover.my.taste.app.service.store.StoreService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.assertj.core.api.Assertions.*
import org.geolatte.geom.builder.DSL
import org.geolatte.geom.crs.CoordinateReferenceSystems
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalTime

private val logger = KotlinLogging.logger {}

@ExtendWith(MockitoExtension::class)
class StoreServiceTest {

    @Mock
    lateinit var storeRepository: StoreRepository

    @Mock
    lateinit var storeCustomRepository: StoreCustomRepository

    @InjectMocks
    lateinit var storeService: StoreService

    private lateinit var saveRequest: StoreDTO.Save

    private lateinit var storePointsResponse: ArrayList<StoreDTO.StorePoint>

    private val point1: StoreDTO.StorePoint = StoreDTO.StorePoint(123.456, 35.678)
    private val point2: StoreDTO.StorePoint = StoreDTO.StorePoint(142.123, 33.777)

    @BeforeEach
    fun setUp() {
        storePointsResponse = ArrayList()

        saveRequest = StoreDTO.Save(
            "새로운 카페",
            123.456,
            35.678,
            "010-0111-0111",
            "공지",
            "짧은 소개",
            "홈페이지주소",
            ParkStatus.YES,
            "가능",
            "12345",
            "우리집",
            "느그집",
            "상세하다",
            LocalTime.now(),
            LocalTime.now().plusHours(3),
            LocalTime.now(),
            LocalTime.now(),
            LocalTime.now(),
            "일요일"
        )

        storePointsResponse.add(point1)
        storePointsResponse.add(point2)
    }

    @Test
    @DisplayName("매장 위경도 리스트 조회")
    fun should_GetStorePoints() {
        given(storeCustomRepository.getStorePoints()).willReturn(storePointsResponse)

        val storePoints = storeService.getStorePoints()

        then(storeCustomRepository).should(times(1)).getStorePoints()

        assertThat(storePoints).hasSize(storePointsResponse.size)
        assertThat(storePoints).containsExactly(point1, point2)
    }

    @Test
    @DisplayName("매장 저장")
    fun should_SaveStore() {
        val mockStore = Store(
            1L,
            "새로운 카페",
            123.456,
            35.678,
            DSL.point(CoordinateReferenceSystems.WGS84, DSL.g(123.436, 35.678)),
            "010-1234-1234",
            "홈페이지주소",
            "공지",
            "짧은 소개",
            StoreStatus.OPEN,
            ParkStatus.YES,
            "가능",
            Address("우리집", "느그집", "12345", "상세하다"),
            BusinessTime(
                LocalTime.now(),
                LocalTime.now().plusHours(3),
                LocalTime.now(),
                LocalTime.now(),
                LocalTime.now(),
                "월요일"
            )
        )

        given(storeRepository.save(any())).willReturn(mockStore)

        val id = storeService.saveStore(saveRequest)

        val argumentCaptor: ArgumentCaptor<Store> = ArgumentCaptor.forClass(Store::class.java)

        then(storeRepository).should(times(1)).save(argumentCaptor.capture())

        assertThat(id).isEqualTo(mockStore.id)
    }
}