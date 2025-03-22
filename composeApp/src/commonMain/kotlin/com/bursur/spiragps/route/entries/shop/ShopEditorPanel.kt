package com.bursur.spiragps.route.entries.shop

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bursur.spiragps.components.bulletedlist.BulletPointEditor
import com.bursur.spiragps.editor.components.TextEdit
import com.bursur.spiragps.route.BasePanelEditor
import com.bursur.spiragps.route.data.Entry
import com.bursur.spiragps.theme.SpiraGPSColours
import com.bursur.spiragps.theme.SpiraGPSText

@Composable
fun ShopEditorPanel(entry: Entry, selectedEntry: Entry) {
    var cost by remember { mutableStateOf(entry.cost) }
    var updates by remember { mutableStateOf(0) }
    val buyItems by remember { mutableStateOf(entry.buy) }
    var newBuyItem by remember { mutableStateOf("") }
    val sellItems by remember { mutableStateOf(entry.sell) }
    var newSellItem by remember { mutableStateOf("") }

    if(selectedEntry == entry) {
        BasePanelEditor(border = SpiraGPSColours.shopBorder) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 5.dp)
            ) {
                Text(
                    text = "Cost:",
                    style = SpiraGPSText.typography.info,
                    color = SpiraGPSColours.text
                )
                TextEdit(
                    text = if (cost != 0) cost.toString() else "",
                    placeholderText = "Price...",
                ) {
                    cost = try {
                        it.toInt(10)
                    } catch (e: Exception) {
                        cost
                    }

                    entry.cost = cost
                }
            }

            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = "Sell:",
                style = SpiraGPSText.typography.info,
                color = SpiraGPSColours.text,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
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
                    multiLine = false,
                    onEnterKey = {
                        sellItems.add(newSellItem)
                        newSellItem = ""
                        entry.guide = sellItems
                    },
                    modifier = Modifier.weight(1f)
                ) { newSellItem = it }

                TextButton(
                    onClick = {
                        sellItems.add(newSellItem)
                        newSellItem = ""
                        entry.guide = sellItems
                    }
                ) {
                    Text(
                        text = "Add",
                        style = SpiraGPSText.typography.info,
                        color = SpiraGPSColours.text
                    )
                }
            }

            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = "Buy:",
                style = SpiraGPSText.typography.info,
                color = SpiraGPSColours.text,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
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
                    multiLine = false,
                    onEnterKey = {
                        buyItems.add(newBuyItem)
                        newBuyItem = ""
                        entry.guide = buyItems
                    },
                    modifier = Modifier.weight(1f)
                ) { newBuyItem = it }

                TextButton(
                    onClick = {
                        buyItems.add(newBuyItem)
                        newBuyItem = ""
                        entry.guide = buyItems
                    }
                ) {
                    Text(
                        text = "Add",
                        style = SpiraGPSText.typography.info,
                        color = SpiraGPSColours.text
                    )
                }
            }
        }
    }
    else
        ShopView(entry)
}