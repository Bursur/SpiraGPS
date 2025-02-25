package com.bursur.spiragps.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.bursur.spiragps.preferences.getDarkModePreference
import com.bursur.spiragps.preferences.getKhaegarModePreference

data class ColourScheme(
    val background: Color,
    val onBackground: Color,

    val text: Color,
    val infoBackground: Color,
    val editorControlBackground: Color,

    val fabBackgroundColour: Color,
    val fabIconColour: Color,

    val toggleSelectedTrackColour: Color,
    val toggleUnselectedTrackColour: Color,
    val toggleSelectedThumbColour: Color,
    val toggleUnselectedThumbColour: Color,

    val loadingColour: Color,
    val loadingTrack: Color,
    val loadingText: Color,

    val battleBorder: Color,
    val encounterBorder: Color,
    val trialsBorder: Color,
    val sphereGridBorder: Color,
    val equipmentBorder: Color,
    val blitzballBorder: Color,
    val itemSortBorder: Color,
    val shopBorder: Color,
    val customiseBorder: Color,
    val tipBorder: Color,

    // Items Sort
    val itemSortBackground: ArrayList<Color>,
    val itemSortWeights: ArrayList<FontWeight>,

    // Characters
    val tidus: Color,
    val yuna: Color,
    val kimahri: Color,
    val auron: Color,
    val wakka: Color,
    val rikku: Color,
    val lulu: Color,
    val seymour: Color,
    val enemy: Color,

    // Aeons
    val valefor: Color,
    val ifrit: Color,
    val ixion: Color,
    val shiva: Color,
    val bahamut: Color,

    // Misc.
    val black: Color,
    val bug: Color
)

val lightScheme = ColourScheme(
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF19191C),

    text = Color(0xFF000000),
    infoBackground = Color(0xFFF2F2F2),
    editorControlBackground = Color(0xFFE2E2E2),

    fabBackgroundColour = Color(0xFFD3D3D3),
    fabIconColour = Color(0xFF414A4C),

    toggleSelectedTrackColour = Color(0xFF77DD77),
    toggleUnselectedTrackColour = Color(0xFFFF8080),
    toggleSelectedThumbColour = Color(0xFFD3D3D3),
    toggleUnselectedThumbColour = Color(0xFFD3D3D3),

    loadingColour = Color(0xFFAEC6CF),
    loadingTrack = Color(0xFFCFCFC4),
    loadingText = Color(0xFFAEC6CF),

    battleBorder = Color(0xFFFF8080),
    encounterBorder = Color(0xFFF5ACCD),
    trialsBorder = Color(0xFFDE9A73),
    sphereGridBorder = Color(0xFFDF809F),
    equipmentBorder = Color(0xFFC7C8CA),
    blitzballBorder = Color(0xFFD48C82),
    itemSortBorder = Color(0xFF77DD77),
    shopBorder = Color(0xFF8080FF),
    customiseBorder = Color(0xFFAEC6CF),
    tipBorder = Color(0xFFFDFD96),

    // Items Sort
    itemSortBackground = arrayListOf(Color(0xFFDDDDDD), Color(0xFFF2F2F2)),
    itemSortWeights = arrayListOf(FontWeight.Light, FontWeight.Bold),

    // Characters
    tidus = Color(0xFF0000FE),
    yuna = Color(0xFF80818D),
    kimahri = Color(0xFF00AFB3),
    auron = Color(0xFFF91D02),
    wakka = Color(0xFFF6911E),
    rikku = Color(0xFF009A57),
    lulu = Color(0xFFBF056D),
    seymour = Color(0xFFA1CD6F),
    enemy = Color(0xFFED058E),

    // Aeons
    valefor = Color(0xFFF39188),
    ifrit = Color(0xFFEE1580),
    ixion = Color(0xFFF39DC4),
    shiva = Color(0xFF1CAEEF),
    bahamut = Color(0xFF6241A8),

    // Misc.
    black = Color(0xFF000000),
    bug = Color(0xFFFF0000)
)

val darkScheme = ColourScheme(
    background = Color(0xFF222222),
    onBackground = Color(0xFF19191C),

    text = Color(0xFFC7C7C7),
    infoBackground = Color(0xFF3C3C3C),
    editorControlBackground = Color(0xFF4C4C4C),

    fabBackgroundColour = Color(0xFFD3D3D3),
    fabIconColour = Color(0xFF414A4C),

    toggleSelectedTrackColour = Color(0xFF2F952F),
    toggleUnselectedTrackColour = Color(0xFFB73838),
    toggleSelectedThumbColour = Color(0xFFD3D3D3),
    toggleUnselectedThumbColour = Color(0xFFD3D3D3),

    loadingColour = Color(0xFFAEC6CF),
    loadingTrack = Color(0xFFCFCFC4),
    loadingText = Color(0xFFAEC6CF),

    battleBorder = Color(0xFFB73838),
    encounterBorder = Color(0xFF7C4A88),
    trialsBorder = Color(0xFF96522B),
    sphereGridBorder = Color(0xFF973857),
    equipmentBorder = Color(0xFF7F8082),
    blitzballBorder = Color(0xFF8C443A),
    itemSortBorder = Color(0xFF2F952F),
    shopBorder = Color(0xFF3D3DBC),
    customiseBorder = Color(0xFF667E87),
    tipBorder = Color(0xFF788046),

    // Items Sort
    itemSortBackground = arrayListOf(Color(0xFF444444), Color(0xFF3C3C3C)),
    itemSortWeights = arrayListOf(FontWeight.Light, FontWeight.Bold),

    // Characters
    tidus = Color(0xFF386FD9),
    yuna = Color(0xFF80818D),
    kimahri = Color(0xFF00AFB3),
    auron = Color(0xFFF91D02),
    wakka = Color(0xFFF6911E),
    rikku = Color(0xFF009A57),
    lulu = Color(0xFFBF056D),
    seymour = Color(0xFFA1CD6F),
    enemy = Color(0xFFED058E),

    // Aeons
    valefor = Color(0xFFF39188),
    ifrit = Color(0xFFEE1580),
    ixion = Color(0xFFF39DC4),
    shiva = Color(0xFF1CAEEF),
    bahamut = Color(0xFF6241A8),

    // Misc.
    black = Color(0xFFFFFFFF),
    bug = Color(0xFFFF0000)
)

val khaegarScheme = ColourScheme(
    background = Color(0xFFED9CAC),
    onBackground = Color(0xFF19191C),

    text = Color(0xFF68303E),
    infoBackground = Color(0xFFED9CAC),
    editorControlBackground = Color(0xFFFFE5E9),

    fabBackgroundColour = Color(0xFFFFBAC5),
    fabIconColour = Color(0xFF414A4C),

    toggleSelectedTrackColour = Color(0xFF77DD77),
    toggleUnselectedTrackColour = Color(0xFFFF8080),
    toggleSelectedThumbColour = Color(0xFFD3D3D3),
    toggleUnselectedThumbColour = Color(0xFFD3D3D3),

    loadingColour = Color(0xFFBF7190),
    loadingTrack = Color(0xFFCFCFC4),
    loadingText = Color(0xFFAEC6CF),

    battleBorder = Color(0xFFFF8080),
    encounterBorder = Color(0xFFF5ACCD),
    trialsBorder = Color(0xFFDE9A73),
    sphereGridBorder = Color(0xFFDF809F),
    equipmentBorder = Color(0xFFC7C8CA),
    blitzballBorder = Color(0xFFD48C82),
    itemSortBorder = Color(0xFF77DD77),
    shopBorder = Color(0xFF8080FF),
    customiseBorder = Color(0xFFAEC6CF),
    tipBorder = Color(0xFFFDFD96),

    // Items Sort
    itemSortBackground = arrayListOf(Color(0xFFE58EA1), Color(0xFFCE7388)),
    itemSortWeights = arrayListOf(FontWeight.Light, FontWeight.Bold),

    // Characters
    tidus = Color(0xFF0000FE),
    yuna = Color(0xFF80818D),
    kimahri = Color(0xFF00AFB3),
    auron = Color(0xFFF91D02),
    wakka = Color(0xFFF6661E),
    rikku = Color(0xFF009A57),
    lulu = Color(0xFFBF056D),
    seymour = Color(0xFFA1CD6F),
    enemy = Color(0xFFED058E),

    // Aeons
    valefor = Color(0xFFF3EC88),
    ifrit = Color(0xFFEE1580),
    ixion = Color(0xFFB69DF3),
    shiva = Color(0xFF1CAEEF),
    bahamut = Color(0xFF6241A8),

    // Misc.
    black = Color(0xFF000000),
    bug = Color(0xFFFF0000)
)

var SpiraGPSColours by mutableStateOf(
    when {
        getKhaegarModePreference() == 1 ->  khaegarScheme
        getDarkModePreference() == 1 -> darkScheme
        else -> lightScheme
    }
)
var SpiraGPSDarkMode by mutableStateOf(getDarkModePreference() == 1)
var SpiraGPSKhaegarMode by mutableStateOf(getKhaegarModePreference() == 1)