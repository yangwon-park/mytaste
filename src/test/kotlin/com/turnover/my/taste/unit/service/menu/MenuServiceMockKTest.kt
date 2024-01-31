package com.turnover.my.taste.unit.service.menu

import com.turnover.my.taste.app.domain.embedded.Address
import com.turnover.my.taste.app.domain.embedded.BusinessTime
import com.turnover.my.taste.app.domain.menu.Menu
import com.turnover.my.taste.app.domain.menu.dto.MenuDTO
import com.turnover.my.taste.app.domain.store.Store
import com.turnover.my.taste.app.domain.store.enums.ParkStatus
import com.turnover.my.taste.app.domain.store.enums.StoreStatus
import com.turnover.my.taste.app.exception.EntityNotFoundException
import com.turnover.my.taste.app.repository.menu.MenuCustomRepository
import com.turnover.my.taste.app.repository.menu.MenuRepository
import com.turnover.my.taste.app.repository.store.StoreRepository
import com.turnover.my.taste.app.service.menu.MenuService
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.*
import org.geolatte.geom.builder.DSL
import org.geolatte.geom.crs.CoordinateReferenceSystems
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalTime

@ExtendWith(MockKExtension::class)
class MenuServiceMockKTest {

    private val menuRepository = mockk<MenuRepository>()
    private val menuCustomRepository = mockk<MenuCustomRepository>()
    private val storeRepository = mockk<StoreRepository>()

    private val menuService = MenuService(menuRepository, menuCustomRepository, storeRepository)

    private var expectedSavedMenu: Menu = Menu(
        id = 1L,
        name = "메뉴1",
        price = 12000,
        isSignature = false,
        intro = "내가 맛있어요"
    )

    @Test
    fun `메뉴 등록 성공`() {
        val saveRequest = MenuDTO.Save(
            storeId = 1L,
            name = "메뉴1",
            price = 12000,
            intro = "내가 맛있어요"
        )

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

        val expectedMenuId = expectedSavedMenu.id

        every { menuRepository.save(any(Menu::class)) } returns expectedSavedMenu
        every { storeRepository.findByIdOrNull(saveRequest.storeId) } returns mockStore

        val result = menuService.saveMenu(saveRequest)

        assertThat(expectedMenuId).isEqualTo(result)
        assertThat(mockStore.menus).contains(expectedSavedMenu)

        verify { menuRepository.save(any(Menu::class)) }
        verify { storeRepository.findByIdOrNull(saveRequest.storeId) }
    }

    @Test
    fun `메뉴 등록 실패 - 없는 매장에 메뉴 등록`() {
        val saveRequest = MenuDTO.Save(
            storeId = 1L,
            name = "메뉴1",
            price = 12000,
            intro = "내가 맛있어요"
        )

        every { menuRepository.save(any(Menu::class))} returns expectedSavedMenu
        every { storeRepository.findByIdOrNull(saveRequest.storeId) } returns null

        assertThatThrownBy {
            menuService.saveMenu(saveRequest)
        }.isInstanceOf(EntityNotFoundException::class.java)

        verify { menuRepository.save(any(Menu::class)) }
        verify { storeRepository.findByIdOrNull(saveRequest.storeId) }
    }
}