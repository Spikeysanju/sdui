package com.thisux.sdui

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed interface UIComponent {
    val type: String
}

@Serializable
@SerialName("banner")
data class BannerUI(
    override val type: String,
    val title: String,
    val description: String,
    val countdown: String,
    val backgroundColor: String
) : UIComponent

@Serializable
@SerialName("card")
data class CardUI(
    override val type: String,
    val title: String,
    val imageUrl: String,
    val description: String
) : UIComponent

@Serializable
@SerialName("button")
data class ButtonUI(
    override val type: String,
    val text: String,
    val action: String
) : UIComponent

// now products grid
@Serializable
@SerialName("product")
data class ProductUI(
    override val type: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val price: String
) : UIComponent

@Serializable
@SerialName("transaction")
data class TransactionUI(
    override val type: String,
    val title: String,
    val amount: String,
    val date: String
): UIComponent

// dashbaord balance and transactions
@Serializable()
@SerialName("dashboard")
data class DashboardUI(
    override val type: String,
    val balance: String,
    val transactions: List<TransactionUI>
) : UIComponent




object JsonParser {
    private val module = SerializersModule {
        polymorphic(UIComponent::class) {
            subclass(BannerUI::class, BannerUI.serializer())
            subclass(CardUI::class, CardUI.serializer())
            subclass(ButtonUI::class, ButtonUI.serializer())
            subclass(ProductUI::class, ProductUI.serializer())
            subclass(DashboardUI::class, DashboardUI.serializer())
            subclass(TransactionUI::class, TransactionUI.serializer())
        }
    }

    val jsonParser = Json {
        ignoreUnknownKeys = true
        serializersModule = module
        classDiscriminator = "_type" // Use a different name for the discriminator
    }

    fun parseJsonToComponent(json: String): UIComponent? {
        return try {
            jsonParser.decodeFromString(UIComponent.serializer(), json)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
