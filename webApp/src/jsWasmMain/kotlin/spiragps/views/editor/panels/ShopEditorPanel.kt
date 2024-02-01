package spiragps.views.editor.panels

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import spiragps.data.route.Entry
import spiragps.style.SpiraGPSColours
import spiragps.style.SpiraGPSText
import spiragps.views.components.TextEdit
import spiragps.views.editor.BulletPointEditor

@Composable
fun ShopEditorPanel(entry: Entry) {
    var cost by remember { mutableStateOf(entry.cost) }
    var updates by remember { mutableStateOf(0) }
    val buyItems by remember { mutableStateOf(entry.buy) }
    var newBuyItem by remember { mutableStateOf("") }
    val sellItems by remember { mutableStateOf(entry.sell) }
    var newSellItem by remember { mutableStateOf("") }

    BasePanelEditor(border = SpiraGPSColours.value.shopBorder) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Text(text = "Cost:", style = SpiraGPSText.typography.info,)
            TextEdit(
                text = if (cost != 0) cost.toString() else "",
                placeholderText = "Price..."
            ) {
                cost = try {
                    it.toInt(10)
                } catch (e: Exception) {
                    cost
                }

                entry.cost = cost
            }
        }

        Divider(color = SpiraGPSColours.value.infoBackground, thickness = 5.dp)
        Text(text = "Buy:", style = SpiraGPSText.typography.info, modifier = Modifier.padding(horizontal = 5.dp))
        key(updates) {
            buyItems.forEachIndexed { index, item ->
                BulletPointEditor(
                    text = item,
                    placeholderText = "Update Item...",
                    onUpdated = {
                        buyItems[index] = it
                        entry.guide = buyItems
                    },
                    onDeleted = {
                        buyItems.removeAt(index)
                        entry.guide = buyItems
                        ++updates
                    }
                )
            }
        }

        Row(modifier = Modifier.padding(horizontal = 10.dp)) {
            TextEdit(
                text = newBuyItem,
                placeholderText = "Add Item to Buy...",
                modifier = Modifier.weight(1f)
            ) { newBuyItem = it }

            TextButton(
                onClick = {
                    buyItems.add(newBuyItem)
                    newBuyItem = ""
                    entry.guide = buyItems
                }
            ) {
                Text(text = "Add", style = SpiraGPSText.typography.info, color = SpiraGPSColours.value.text)
            }
        }

        Divider(color = SpiraGPSColours.value.infoBackground, thickness = 5.dp)
        Text(text = "Sell:", fontFamily = SpiraGPSText.fontFamily, modifier = Modifier.padding(horizontal = 5.dp))
        key(updates) {
            sellItems.forEachIndexed { index, item ->
                BulletPointEditor(
                    text = item,
                    placeholderText = "Update Item...",
                    onUpdated = {
                        sellItems[index] = it
                        entry.guide = sellItems
                    },
                    onDeleted = {
                        sellItems.removeAt(index)
                        entry.guide = sellItems
                        ++updates
                    }
                )
            }
        }

        Row(modifier = Modifier.padding(horizontal = 10.dp)) {
            TextEdit(
                text = newSellItem,
                placeholderText = "Add Item to Sell...",
                modifier = Modifier.weight(1f)
            ) { newSellItem = it }

            TextButton(
                onClick = {
                    sellItems.add(newSellItem)
                    newSellItem = ""
                    entry.guide = sellItems
                }
            ) {
                Text(text = "Add", style = TextStyle(fontFamily = SpiraGPSText.fontFamily, color = Color.Black))
            }
        }
    }
}