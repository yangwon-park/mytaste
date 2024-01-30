package com.turnover.my.taste.unit.service.menu

import com.turnover.my.taste.app.domain.embedded.Address
import com.turnover.my.taste.app.domain.embedded.BusinessTime
import com.turnover.my.taste.app.domain.menu.Menu
import com.turnover.my.taste.app.domain.menu.dto.MenuDTO
import com.turnover.my.taste.app.domain.store.Store
import com.turnover.my.taste.app.domain.store.enums.ParkStatus
import com.turnover.my.taste.app.domain.store.enums.StoreStatus
import com.turnover.my.taste.app.repository.menu.MenuCustomRepository
import com.turnover.my.taste.app.repository.menu.MenuRepository
import com.turnover.my.taste.app.service.menu.MenuService
import org.geolatte.geom.builder.DSL
import org.geolatte.geom.crs.CoordinateReferenceSystems
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import java.time.LocalTime

@ExtendWith(MockitoExtension::class)
class MenuServiceTest {

    @Mock
    lateinit var menuRepository: MenuRepository

    @Mock
    lateinit var menuCustomRepository: MenuCustomRepository

    @InjectMocks
    lateinit var menuService: MenuService

    private lateinit var saveRequest: MenuDTO.Save

    private lateinit var store: Store

    @BeforeEach
    fun setUp() {
        saveRequest = MenuDTO.Save(
            storeId = 1L,
            name = "메뉴1",
            price = 12000,
            imageUrl = "URL",
            intro = "내가 맛있어요"
        )

        store = Store(
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
            Address(
                "우리집",
                "느그집",
                "12345",
                "상세하다"),
            BusinessTime(
                LocalTime.now(),
                LocalTime.now().plusHours(3),
                LocalTime.now(),
                LocalTime.now(),
                LocalTime.now(),
                "월요일"
            )
        )
    }

    @Test
    @DisplayName("메뉴 등록 성공")
    fun should_SucceedSavingMenu() {
        val mockMenu = Menu(
            id = 1L,
            name = "메뉴1",
            price = 12000,
            imageUrl = "URL",
            isSignature = false,
            intro = "내가 맛있어요"
        )

        given(menuRepository.save(any())).willReturn(mockMenu)

        menuService.saveMenu(saveRequest)
    }
}