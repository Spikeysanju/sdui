package com.thisux.sdui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.thisux.sdui.ui.theme.SduiTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SduiTheme {
                MultipleComponentsScreen()
            }
        }
    }
}


@Composable
fun RenderUIComponents(components: List<UIComponent>) {
    val scrollState = rememberScrollState()


    Column(modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState)) {
        components.forEach { component ->
            when (component) {
                is BannerUI -> RenderBanner(component)
                is CardUI -> RenderCard(component)
                is ButtonUI -> RenderButton(component)
                is ProductUI -> RenderProduct(component)
                is TransactionUI -> RenderTransaction(component)
                is DashboardUI -> RenderDashboard(component)

            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MultipleComponentsScreen() {
    val serverJson = """
        [
         
          {
            "_type": "card",
            "type": "card",
            "title": "Exclusive Offer",
            "imageUrl": "https://example.com/image.jpg",
            "description": "Get 20% off on your first order!",
            "backgroundColor": "#FFAA00"
          },
           {
              "_type": "banner",
            "type": "banner",
            "title": "Welcome!",
            "description": "Start your journey with us.",
            "countdown": "2024-12-31T23:59:59Z",
            "backgroundColor": "#FFAA00"
          },
          {
            "_type": "button",
            "type": "button",
            "text": "Learn More",
            "action": "https://example.com/learn"
          },
            {
                "_type": "product",
                "type": "product",
                "title": "Product 1",
                "imageUrl": "https://example.com/image.jpg",
                "description": "Product 1 description",
                "price": "$10.00"
            },
            {
                "_type": "product",
                "type": "product",
                "title": "Product 2",
                "imageUrl": "https://example.com/image.jpg",
                "description": "Product 2 description",
                "price": "$20.00"
            },
            {
                "_type": "product",
                "type": "product",
                "title": "Product 3",
                "imageUrl": "https://example.com/image.jpg",
                "description": "Product 3 description",
                "price": "$30.00"
            },{
            "_type": "transaction",
            "type": "transaction",
            "title": "Transaction 1",
            "amount": "$10.00",
            "date": "2024-12-31T23:59:59Z"},
            {
            "_type": "dashboard",
            "type": "dashboard",
            "balance": "$100.00",
            "transactions": [
                {
                    "_type": "transaction",
                    "type": "transaction",
                    "title": "Transaction 1",
                    "amount": "$10.00",
                    "date": "2024-12-31T23:59:59Z"
                },
                {
                    "_type": "transaction",
                    "type": "transaction",
                    "title": "Transaction 2",
                    "amount": "$20.00",
                    "date": "2024-12-31T23:59:59Z"
                }
            ]
            }
            ]
            
    """.trimIndent()

    val components = JsonParser.jsonParser.decodeFromString<List<UIComponent>>(serverJson)
    if (components != null) {
        RenderUIComponents(components)
    } else {
        Log.e("Parsing Error", "Failed to parse UI components")
        Text("Error: Failed to load UI components.")
    }
}

@Composable
fun RenderBanner(banner: BannerUI) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF6A11CB),
                        Color(0xFF2575FC)
                    )
                )
            )
            .padding(24.dp)
    ) {
        Column {
            Text(
                text = banner.title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = banner.description,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White.copy(alpha = 0.87f)
                )
            )

            banner.countdown?.let { countdownDate ->

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Offer ends: 12 days",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White.copy(alpha = 0.7f)
                    )
                )
            }
        }
    }
}

@Composable
fun RenderCard(card: CardUI) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFF6D365),
                            Color(0xFFFDA085)
                        )
                    )
                )
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = card.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = card.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White.copy(alpha = 0.87f)
                    )
                )
            }
        }
    }
}

@Composable
fun RenderProduct(product: ProductUI) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Placeholder for product image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        color = Color(0xFFF0F0F0),
                        shape = RoundedCornerShape(8.dp)
                    )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.Bold
                    )
                )

                Button(
                    onClick = { /* Handle product action */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2196F3)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Add to Cart")
                }
            }
        }
    }
}

@Composable
fun RenderButton(button: ButtonUI) {
    Button(
        onClick = { /* Handle button action */ },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF673AB7)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp
        )
    ) {
        Text(
            text = button.text,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun RenderTransaction(transaction: TransactionUI) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = transaction.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = transaction.date,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Gray
                    )
                )
            }

            Text(
                text = transaction.amount,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun RenderDashboard(dashboard: DashboardUI) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Total Balance",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Gray
                )
            )
            Text(
                text = dashboard.balance,
                style = MaterialTheme.typography.displaySmall.copy(
                    color = Color(0xFF4CAF50),
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Recent Transactions",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            dashboard.transactions.forEach { transaction ->
                RenderTransaction(transaction)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MultipleComponentsPreview() {
    val components = listOf(
        BannerUI(
            type = "banner",
            title = "Welcome!",
            description = "Start your journey with us.",
            countdown = "2024-12-31T23:59:59Z",
            backgroundColor = "#FFAA00"
        ),
        CardUI(
            type = "card",
            title = "Exclusive Offer",
            imageUrl = "https://example.com/image.jpg",
            description = "Get 20% off on your first order!"
        ),
        ButtonUI(
            type = "button",
            text = "Learn More",
            action = "https://example.com/learn"
        )
    )
    RenderUIComponents(components)
}