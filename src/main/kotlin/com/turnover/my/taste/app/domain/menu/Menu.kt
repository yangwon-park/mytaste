package com.turnover.my.taste.app.domain.menu

import com.turnover.my.taste.app.domain.common.BaseEntity
import com.turnover.my.taste.app.domain.store.Store
import jakarta.persistence.*

@Entity
@Table(name = "menu", schema = "app")
class Menu(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    val id: Long? = null,

    @Column(length = 30)
    val name: String,

    val price: Int,

    @Column(name = "is_signature")
    val isSignature: Boolean,

    @Column(name = "intro", length = 200)
    val intro: String,

    ) : BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    var store: Store? = null

    fun linkStore(store: Store) {
        this.store = store
    }
}