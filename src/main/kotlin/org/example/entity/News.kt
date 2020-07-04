package org.example.entity

import javax.persistence.*

@Entity
@Table(name = "news")
class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var title: String? = null
    var text: String? = null

    constructor() {}

    constructor(id: Long?, title: String?, text: String?) {
        this.id = id
        this.title = title
        this.text = text
    }
}