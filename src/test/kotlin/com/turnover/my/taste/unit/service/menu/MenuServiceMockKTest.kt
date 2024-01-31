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
import com.turnover.my.taste.app.repository.store.StoreRepository
import com.turnover.my.taste.app.service.menu.MenuService
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
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

    @Test
    fun `saveMenu saves and links menu correctly`() {
        val saveRequest = MenuDTO.Save(
            storeId = 1L,
            name = "메뉴1",
            price = 12000,
            intro = "내가 맛있어요"
        )

        val expectedSavedMenu =  Menu(
            id = 1L,
            name = "메뉴1",
            price = 12000,
            isSignature = false,
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

        verify { storeRepository.findByIdOrNull(saveRequest.storeId) }
        verify { menuRepository.save(any(Menu::class)) }
    }

//    @Test
//    fun `saveMenu throws exception for invalid store id`() {
//        val invalidMenuDto = MenuDTO.Save(
//            storeId = 1L,
//            name = "메뉴1",
//            price = 12000,
//            intro = "내가 맛있어요"
//        )
//
//        every { storeRepository.findByIdOrNull(invalidMenuDto.storeId) } returns null
//
//        assertThrows<EntityNotFoundException> {
//            menuService.saveMenu(invalidMenuDto)
//        }
//
//        verify { storeRepository.findByIdOrNull(invalidMenuDto.storeId) }
//    }
}