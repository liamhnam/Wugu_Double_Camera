package com.brother.sdk.usb;

import android.hardware.usb.UsbDevice;
import android.os.Build;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import com.brother.sdk.print.pdl.PrintImageUtil;
import com.google.android.material.internal.ViewUtils;
import com.tom_roush.fontbox.ttf.WGL4Names;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.apache.log4j.spi.Configurator;

public class BrUsbDevice implements Serializable {
    private static final String BROTHER_MODEL_PREFIX = "Brother ";
    private static final HashMap<Integer, String> BROTHER_USB_MODEL_TABLES = new HashMap<Integer, String>() {
        private static final long serialVersionUID = 1;

        {
            put(24745, "ADS-1600W");
            put(24744, "ADS-1500W");
            put(24743, "ADS-1100W");
            put(24742, "ADS-1000W");
            put(24741, "ADS-2600W");
            put(24740, "ADS-2500W");
            put(24737, "ADS-2100");
            put(24736, "ADS-2000");
            put(954, "ADS-3600W");
            put(953, "ADS-2800W");
            put(952, "ADS-3000N");
            put(895, "ADS-2600We");
            put(894, "ADS-2500We");
            put(951, "ADS-2400N");
            put(893, "ADS-2100e");
            put(892, "ADS-2000e");
            put(788, "MFC-9995CDW");
            put(787, "MFC-9990CDW");
            put(786, "MFC-9565CDW");
            put(785, "MFC-9480CDW");
            put(784, "DCP-9290CDW");
            put(783, "DCP-9075CDN");
            put(782, "DCP-J112");
            put(781, "ADS-1650W");
            put(780, "ADS-1550W");
            put(779, "ADS-1150W");
            put(778, "ADS-1050W");
            put(769, "MFC-J3920");
            put(Integer.valueOf(ViewUtils.EDGE_TO_EDGE_FLAGS), "MFC-J3720");
            put(767, "MFC-J3520");
            put(766, "MFC-J200");
            put(765, "DCP-J105");
            put(764, "DCP-J100");
            put(763, "MFC-J875DW");
            put(762, "MFC-J450DW");
            put(761, "DCP-J132N");
            put(760, "MFC-J6770CDW");
            put(759, "MFC-J6975CDW");
            put(758, "MFC-J6970CDW");
            put(757, "MFC-J6920DW");
            put(756, "MFC-J6720DW");
            put(755, "MFC-J6570CDW");
            put(754, "MFC-J6520DW");
            put(753, "MFC-J890DN");
            put(752, "MFC-J980DN");
            put(751, "MFC-J820DN");
            put(750, "MFC-J720D");
            put(749, "MFC-J870N");
            put(748, "MFC-J870DW");
            put(747, "MFC-J650DW");
            put(746, "MFC-J285DW");
            put(745, "MFC-J475DW");
            put(744, "MFC-J470DW");
            put(743, "MFC-J245");
            put(742, "DCP-J952N");
            put(741, "DCP-J752N");
            put(740, "DCP-J752DW");
            put(739, "DCP-J552N");
            put(738, "DCP-J552DW");
            put(737, "DCP-J172W");
            put(736, "DCP-J152N");
            put(735, "DCP-J152W");
            put(734, "DCP-J132W");
            put(733, "DCP-J4215N");
            put(732, "MFC-J4615DW");
            put(731, "MFC-J4605DW");
            put(730, "MFC-J4515DW");
            put(729, "MFC-J4505DW");
            put(728, "MFC-J4415DW");
            put(727, "MFC-J4405DW");
            put(726, "MFC-J4315DW");
            put(725, "MFC-J4305DW");
            put(724, "MFC-8810DW");
            put(723, "DCP-9020CDW");
            put(721, "MFC-1810");
            put(720, "DCP-1510");
            put(719, "DCP-7057W");
            put(718, "DCP-7055W");
            put(717, "MFC-J2510");
            put(716, "MFC-J2310");
            put(715, "MFC-8710DW");
            put(714, "MFC-8712DW");
            put(713, "MFC-J4810DN");
            put(712, "MFC-J4910CDW");
            put(711, "MFC-J4510N");
            put(710, "DCP-J4210N");
            put(709, "MFC-J4610DW");
            put(708, "MFC-J4410DW");
            put(Integer.valueOf(TypedValues.TransitionType.TYPE_TRANSITION_FLAGS), "MFC-J4310DW");
            put(Integer.valueOf(TypedValues.TransitionType.TYPE_STAGGERED), "DCP-J4110DW");
            put(Integer.valueOf(TypedValues.TransitionType.TYPE_INTERPOLATOR), "MFC-J960DN");
            put(Integer.valueOf(TypedValues.TransitionType.TYPE_AUTO_TRANSITION), "DCP-J940N");
            put(703, "MFC-J840N");
            put(Integer.valueOf(TypedValues.TransitionType.TYPE_TO), "MFC-J710D");
            put(Integer.valueOf(TypedValues.TransitionType.TYPE_FROM), "DCP-J740N");
            put(700, "DCP-J540N");
            put(699, "MFC-8952DW");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_SIZE), "MFC-8912DW");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_CUSTOM), "MFC-8712DN");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_CLEAR), "MFC-8512DN");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_REDO), "DCP-8157DN");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_UNDO), "DCP-8152DN");
            put(693, "DCP-8112DN");
            put(692, "MFC-J4710DW");
            put(691, "MFC-J4510DW");
            put(690, "MFC-J810DN");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_PRICE_POS), "DCP-9020CDN");
            put(688, "MFC-9340CDW");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_BTN_SIGN), "MFC-9330CDW");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_COUNTDOWN), "MFC-9140CDN");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_BTN_BACK), "MFC-9130CW");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_BTN_ADD_TEXT), "DCP-8110D");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_QRCODE1_TIPS), "FAX-2990");
            put(682, "FAX-2890");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_QRCODE1), "FAX-1740");
            put(680, "MFC-7290");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_BTN_BACK_HOME), "FAX-2950");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_FRAME_POS), "FAX-2940");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_BTN_PRINT), "MFC-7240");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_COUNT_NUM), "FAX-2845");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_COUNT_SUB), "FAX-2840");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_COUNT_ADD), "MFC-9015CN");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_COUNT_TEXT), "DCP-9015CN");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_BG_SIGN), "DCP-J140W");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_BG_STICKER), "MFC-9325CW");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_STICKER_BG), "MFC-9125CN");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_STICKER_SIGN_POS), "MFC-8510D");
            put(668, "MFC-8515DN");
            put(Integer.valueOf(UiPosIndexEnum.PRINT_SIGN_BG), "FAX-2810N");
            put(666, "MFC-8690DW");
            put(665, "MFC-8950DW");
            put(664, "MFC-8910DW");
            put(663, "MFC-8710DN");
            put(662, "MFC-8520DN");
            put(661, "MFC-8510DN");
            put(660, "DCP-8250DN");
            put(659, "DCP-8155DN");
            put(658, "DCP-8150DN");
            put(657, "DCP-8110DN");
            put(656, "MFC-J432W");
            put(655, "MFC-J425W");
            put(654, "MFC-J275W");
            put(653, "MFC-J835DW");
            put(652, "MFC-J635DW");
            put(651, "MFC-7362N");
            put(650, "DCP-J925N");
            put(649, "MFC-J5910CDW");
            put(648, "MFC-J5910DW");
            put(647, "MFC-J860DN");
            put(646, "MFC-J810D");
            put(645, "MFC-J705D");
            put(644, "MFC-J825N");
            put(643, "MFC-J825DW");
            put(642, "MFC-J625DW");
            put(641, "MFC-J430W");
            put(640, "MFC-J435W");
            put(639, "MFC-J280W");
            put(638, "MFC-J955DN");
            put(637, "DCP-J925DW");
            put(636, "DCP-J725N");
            put(635, "DCP-J725DW");
            put(634, "DCP-J525N");
            put(633, "DCP-J525W");
            put(632, "MFC-J410W");
            put(631, "DCP-7070DW");
            put(630, "MFC-5895CW");
            put(629, "FAX-7860DW");
            put(628, "MFC-7362");
            put(627, "DCP-7057");
            put(626, "HL-2280DW");
            put(625, "MFC-7470D");
            put(624, "MFC-7360N");
            put(623, "MFC-J270W");
            put(622, "MFC-J855DN");
            put(621, "MFC-J805D");
            put(620, "MFC-J705D");
            put(619, "MFC-J630W");
            put(616, "MFC-J6910CDW");
            put(615, "MFC-J6910DW");
            put(614, "MFC-J6710CDW");
            put(613, "MFC-J6510CDW");
            put(Integer.valueOf(TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID), "MFC-J6310W");
            put(Integer.valueOf(TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_TYPE), "MFC-J6710DW");
            put(Integer.valueOf(TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS), "MFC-J6510DW");
            put(Integer.valueOf(TypedValues.MotionType.TYPE_POLAR_RELATIVETO), "MFC-J850DN");
            put(Integer.valueOf(TypedValues.MotionType.TYPE_DRAW_PATH), "MFC-J800D");
            put(Integer.valueOf(TypedValues.MotionType.TYPE_PATHMOTION_ARC), "MFC-J700D");
            put(Integer.valueOf(TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO), "MFC-J615N");
            put(Integer.valueOf(TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO), "MFC-J615W");
            put(Integer.valueOf(TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR), "MFC-J415W");
            put(Integer.valueOf(TypedValues.MotionType.TYPE_EASING), "MFC-J265W");
            put(Integer.valueOf(TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE), "MFC-J410");
            put(601, "MFC-J220");
            put(600, "DCP-J715N");
            put(599, "DCP-J715W");
            put(598, "DCP-J515N");
            put(597, "DCP-J515W");
            put(596, "DCP-J315W");
            put(595, "DCP-J125");
            put(594, "MFC-7065DN");
            put(593, "MFC-7060D");
            put(592, "MFC-7055");
            put(591, "MFC-7860DW");
            put(590, "MFC-7460DN");
            put(589, "MFC-7360");
            put(588, "MFC-7860DN");
            put(587, "MFC-7560D");
            put(586, "DCP-7065DN");
            put(585, "DCP-7060D");
            put(584, "DCP-7055");
            put(582, "MFC-9970CDW");
            put(581, "MFC-9560CDW");
            put(580, "MFC-9465CDN");
            put(579, "MFC-9460CDN");
            put(578, "DCP-9270CDN");
            put(577, "DCP-9055CDN");
            put(576, "MFC-J950DN");
            put(575, "MFC-8680DN");
            put(574, "DCP-197C");
            put(573, "DCP-193C");
            put(572, "DCP-391CN");
            put(571, "DCP-191C");
            put(570, "MFC-257CW");
            put(569, "MFC-253CW");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_CONFIRM_BG), "DCP-597CW");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_PREVIEW_BG), "DCP-593CW");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_FILTER_LIST), "DCP-390CN");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_EFFECT_FILTER_BG), "DCP-377CW");
            put(564, "DCP-373CW");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_PHOTO), "DCP-190C");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_TV_RETAKE_COUNT), "DCP-177C");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_COUNTDOWN), "DCP-173C");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_POS), "MFC-935CDN");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_EFFECT), "MFC-735CD");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_MAKEUP), "MFC-695CDN");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_FILTER), "MFC-675CD");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_RETAKE), "MFC-795CW");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BTN_CONFIRM), "MFC-495CN");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_RETAKE_LIST), "MFC-495CW");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_EFFECT_FILTER_POS), "MFC-295CN");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_PICTURE_LIST), "MFC-255CW");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_PREVIEW_POS), "DCP-595CN");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_THREE), "DCP-595CW");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_TWO), "DCP-395CN");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_ONE), "DCP-375CW");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_NONE), "DCP-365CN");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_BEAUTY_TITLE), "DCP-195C");
            put(545, "DCP-175C");
            put(544, "MFC-9010CN");
            put(543, "DCP-8085DN");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_COUNTDOWN_BG), "DCP-9010CN");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_CONFIRM_POS), "MFC-9120CN");
            put(540, "MFC-9320CW");
            put(539, "DCP-8070D");
            put(538, "MFC-8370DN");
            put(537, "MFC-8380DN");
            put(536, "DCP-8080DN");
            put(535, "MFC-8480DN");
            put(534, "MFC-8880DN");
            put(533, "MFC-8890DW");
            put(532, "MFC-7345N");
            put(525, "MFC-9450CDN");
            put(524, "DCP-9042CDN");
            put(523, "FAX-2930");
            put(522, "MFC-8670DN");
            put(521, "FAX-2915");
            put(520, "DCP-167C");
            put(519, "DCP-163C");
            put(518, "DCP-145C");
            put(517, "DCP-185C");
            put(516, "DCP-165C");
            put(515, "DCP-383C");
            put(514, "DCP-387C");
            put(Integer.valueOf(InputDeviceCompat.SOURCE_DPAD), "DCP-385C");
            put(512, "DCP-585CW");
            put(Integer.valueOf(FrameMetricsAggregator.EVERY_DURATION), "DCP-535CN");
            put(Integer.valueOf(TypedValues.PositionType.TYPE_POSITION_TYPE), "MFC-250C");
            put(509, "MFC-290C");
            put(Integer.valueOf(TypedValues.PositionType.TYPE_CURVE_FIT), "MFC-297C");
            put(507, "MFC-490CW");
            put(Integer.valueOf(TypedValues.PositionType.TYPE_PERCENT_X), "MFC-490CN");
            put(505, "MFC-790CW");
            put(504, "MFC-990CW");
            put(503, "MFC-670CD");
            put(502, "MFC-930CDN");
            put(501, "MFC-5490CN");
            put(500, "MFC-5890CN");
            put(499, "MFC-6490CW");
            put(498, "MFC-6490CN");
            put(497, "DCP-6690CW");
            put(496, "MFC-6890CDW");
            put(495, "MFC-6890CN");
            put(494, "MFC-7450");
            put(493, "MFC-7840N");
            put(492, "MFC-9640CW");
            put(491, "MFC-7320");
            put(490, "DCP-7030");
            put(489, "DCP-7040");
            put(488, "DCP-7045N");
            put(487, "MFC-7340");
            put(486, "MFC-7440N");
            put(485, "MFC-7840W");
            put(484, "DCP-357C");
            put(483, "DCP-353C");
            put(482, "DCP-157C");
            put(481, "DCP-153C");
            put(480, "MFC-265C");
            put(479, "DCP-155C");
            put(478, "MFC-880CDN");
            put(477, "MFC-870CDN");
            put(476, "MFC-650CD");
            put(475, "MFC-480CN");
            put(474, "MFC-885CW");
            put(473, "MFC-685CW");
            put(472, "MFC-680CN");
            put(471, "MFC-465CN");
            put(470, "MFC-260C");
            put(469, "MFC-235C");
            put(468, "MFC-230C");
            put(467, "DCP-770CN");
            put(466, "DCP-770CW");
            put(465, "DCP-560CN");
            put(464, "DCP-350C");
            put(463, "DCP-150C");
            put(462, "DCP-135C");
            put(460, "MFC-9840CDW");
            put(459, "DCP-9045CDN");
            put(458, "MFC-9440CN");
            put(457, "DCP-9040CN");
            put(454, "FAX-2580C");
            put(453, "MFC-239C");
            put(452, "DCP-331C");
            put(451, "DCP-329C");
            put(450, "DCP-131C");
            put(449, "DCP-129C");
            put(448, "DCP-128C");
            put(447, "MFC-860CDN");
            put(446, "DCP-750CN");
            put(445, "MFC-8660DN");
            put(444, "FAX-1960C");
            put(443, "FAX-1860C");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_KEYBOARD_POS), "MFC-3360C");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_OK), "FAX-4100e");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_DEL), "MFC-5860CN");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_9), "MFC-5460CN");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_8), "MFC-850CDN");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_7), "MFC-630CD");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_6), "MFC-460CN");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_5), "FAX-2480C");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_4), "MFC-845CW");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_3), "MFC-665CW");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_2), "MFC-660CN");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_1), "MFC-440CN");
            put(Integer.valueOf(UiPosIndexEnum.KEYBOARD_0), "DCP-750CW");
            put(427, "MFC-240C");
            put(426, "DCP-540CN");
            put(Integer.valueOf(TypedValues.CycleType.TYPE_WAVE_PHASE), "DCP-330C");
            put(424, "DCP-130C");
            put(423, "MFC-8870DW");
            put(422, "MFC-8860DN");
            put(421, "MFC-8460N");
            put(420, "DCP-8065DN");
            put(Integer.valueOf(HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE), "DCP-8060");
            put(418, "MFC-8640D");
            put(Integer.valueOf(HttpStatus.SC_GONE), "MFC-840CLN");
            put(Integer.valueOf(HttpStatus.SC_CONFLICT), "MFC-830CLN");
            put(Integer.valueOf(HttpStatus.SC_REQUEST_TIMEOUT), "MFC-615CL");
            put(Integer.valueOf(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED), "MFC-640CW");
            put(Integer.valueOf(HttpStatus.SC_NOT_ACCEPTABLE), "MFC-820CN");
            put(Integer.valueOf(HttpStatus.SC_METHOD_NOT_ALLOWED), "MFC-820CW");
            put(Integer.valueOf(HttpStatus.SC_NOT_FOUND), "MFC-425CN");
            put(403, "MFC-215C");
            put(402, "DCP-340CW");
            put(401, "DCP-315CN");
            put(400, "DCP-120C");
            put(399, "DCP-118C");
            put(398, "DCP-117C");
            put(397, "DCP-116C");
            put(396, "DCP-115C");
            put(395, "FAX-2810");
            put(394, "MFC-9420CN");
            put(392, "FAX-2920");
            put(391, "FAX-2820");
            put(390, "MFC-7225N");
            put(389, "MFC-7220");
            put(388, "DCP-7025");
            put(387, "DCP-7020");
            put(386, "DCP-7010");
            put(385, "MFC-7820N");
            put(384, "MFC-7420");
            put(378, "MFC-8120");
            put(372, "MFC-3340CN");
            put(371, "MFC-3240C");
            put(370, "FAX-1940CN");
            put(369, "FAX-1835C");
            put(368, "FAX-1840C");
            put(366, "MFC-5840CN");
            put(365, "MFC-5440CN");
            put(364, "FAX-2440C");
            put(363, "DCP-310CN");
            put(361, "DCP-110C");
            put(Integer.valueOf(PrintImageUtil.ROUND_ROTATE), "MFC-620CLN");
            put(358, "MFC-610CLN");
            put(357, "MFC-620CN");
            put(355, "MFC-410CN");
            put(354, "MFC-420CN");
            put(353, "MFC-210C");
            put(352, "MFC-8840D");
            put(351, "MFC-8440");
            put(350, "DCP-8045D");
            put(349, "DCP-8040");
            put(346, "MFC-3320CN");
            put(344, "MFC-3820JN");
            put(343, "MFC-3420J");
            put(339, "DCP-1000J");
            put(337, "MFC-8210J");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_XIE_CHENG_IC), "MFC-8220");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_DOU_YIN_BG), "DCP-8025J");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_MEI_TUAN_BG), "MFC-8820J");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_DOU_YIN_IC), "FAX-1815C");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_MEI_TUAN_IC), "DCP-3020C");
            put(Integer.valueOf(UiPosIndexEnum.PHOTO_TRANSITION), "MFC-3820C");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_ARROW_IC), "MFC-3420C");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_CODE_INPUT), "FAX-1920CN");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_CODE_EX_BG), "MFC-3320C");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_BALANCE), "FAX-1820C");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_PRICE_TEXT), "MFC-3220C");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_GAME_COIN_IC), "DCP-4020C");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_CASH_IC), "MFC-8820D");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_COIN_IC), "MFC-8420");
            put(321, "DCP-8025D");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_ZHIFUBAO_IC), "DCP-8020");
            put(Integer.valueOf(UiPosIndexEnum.PAYMENT_WECHAT_IC), "MFC-4820C");
            put(318, "MFC-4420C");
            put(317, "MFC-5200J");
            put(316, "MFC-890");
            put(314, "MFC-3200C");
            put(310, "MFC-150CL");
            put(309, "MFC-100");
            put(306, "MFC-5200C");
            put(304, "FAX-5750e");
            put(303, "FAX-4750e");
            put(302, "FAX-4100");
            put(299, "MFC-9030");
            put(296, "MFC-8500J");
            put(295, "MFC-9800J");
            put(294, "FAX-1800C");
            put(293, "MFC-6800J");
            put(292, "MFC-4800J");
            put(291, "FAX-2850");
            put(290, "MFC-5100J");
            put(289, "MFC-590");
            put(288, "MFC-580");
            put(287, "MFC-9160");
            put(286, "MFC-9180");
            put(285, "MFC-9070");
            put(284, "MFC-9760");
            put(283, "MFC-9880");
            put(282, "MFC-9860");
            put(281, "MFC-9660");
            put(280, "FAX-3800");
            put(279, "FAX-2900");
            put(278, "DCP-1400");
            put(277, "MFC-9800");
            put(276, "MFC-9700");
            put(275, "MFC-8500");
            put(274, "DCP-1000");
            put(273, "MFC-6800");
            put(272, "MFC-4800");
            put(271, "MFC-5100C");
            put(270, "MFC-3100C");
            put(269, "MFC-9200J");
            put(268, "MFC-7400J");
            put(267, "MFC-860");
            put(266, "MFC-840");
            put(265, "MFC-830");
            put(264, "MFC-9200");
            put(263, "MFC-7400");
            put(262, "MFC-7300");
            put(261, "MFC-9600J");
            put(260, "MFC-8300J");
            Integer numValueOf = Integer.valueOf(WGL4Names.NUMBER_OF_MAC_GLYPHS);
            put(numValueOf, "DCP-1200");
            put(numValueOf, "MFC-9750");
            put(257, "MFC-9600");
            put(257, "MFC-9850");
            put(257, "MFC-9870");
            put(256, "FAX-5750");
            put(256, "FAX-8750P");
            put(256, "MFC-8300");
            put(256, "MFC-8600");
            put(256, "MFC-8700");
            put(256, "MFC-9650");
            put(88, "HL-7580CDW");
            put(87, "HL-4580CDW");
            put(86, "HL-4250CDN");
            put(85, "HL-3150CDN");
            put(84, "HL-1110");
            put(83, "HL-3170CDW");
            put(82, "HL-3150CDW");
            put(81, "HL-3140CW");
            put(80, "HL-S7000DN");
            put(79, "HL-3075CW");
            put(78, "HL-3045CN");
            put(77, "HL-6180DW");
            put(76, "HL-5470DW");
            put(75, "HL-5450DN");
            put(74, "HL-5440D");
            put(70, "HL-2220");
            put(69, "HL-2240");
            put(68, "HL-2230");
            put(67, "HL-4140CN");
            put(66, "HL-2270DW");
            put(65, "HL-2250DN");
            put(64, "HL-2240D");
            put(63, "HL-2130");
            put(62, "HL-4570CDW");
            put(61, "HL-4150CDN");
            put(60, "HL-5380DN");
            put(59, "HL-5370DW");
            put(58, "HL-5350DN");
            put(57, "HL-5340D");
            put(56, "HL-3070CW");
            put(55, "HL-3040CN");
            put(54, "HL-4040CDN");
            put(53, "HL-2170W");
            put(52, "HL-2150N");
            put(51, "HL-2140");
            put(50, "HL-2075N");
            put(49, "HL-2045");
            put(48, "HL-4070CDW");
            put(47, "HL-4050CDN");
            put(46, "HL-4040CN");
            put(45, "HL-5280DW");
            put(44, "HL-5270DN");
            put(43, "HL-5250DN");
            put(42, "HL-5240");
            put(41, "HL-2070N");
            put(40, "HL-2040");
            put(39, "HL-2030");
            put(38, "HL-6050DN");
            put(37, "HL-6050D_DN");
            put(36, "HL-6050");
            put(35, "HL-5170DN");
            put(34, "HL-5150D");
            put(33, "HL-5140");
            put(32, "HL-5130");
            put(31, "HL-5070DN");
            put(30, "HL-8050N");
            put(29, "HL-2700CN");
            put(28, "HL-4200CN");
            put(27, "HL-1435");
            put(26, "HL-1430");
            put(24, "HL-5070N");
            put(23, "HL-5050");
            put(22, "HL-5040");
            put(21, "HL-5030");
            put(20, "HL-7050");
            put(19, "HL-1850_1870N");
            put(18, "HL-4000CN");
            put(17, "HL-3450CN");
            put(16, "HL-2600CN");
            put(15, "HL-1470N");
            put(14, "HL-1450");
            put(13, "HL-1440");
            put(12, "HL-2460");
            put(11, "HL-1650_1670N");
            put(10, "HL-P2500");
            put(8, "HL-1270N");
            put(7, "HL-1250");
            put(6, "HL-1240");
            put(5, "HL-1220");
            put(4, "HL-2060");
            put(3, "HL-1070");
            put(2, "HL-1050");
            put(1, "HL-1660e");
        }
    };
    private static final int BROTHER_VENDOR_ID = 1273;
    private static final String SERIES_SUFFIX = " series";
    private static final String USB_CONNECTION_ID = "USB Connected";
    private static final String USB_DEFAULT_MANUFACTURER = "Brother";
    public static final String USB_DEFAULT_PRODUCT = "Brother Model with USB";
    private static final String USB_SAMPLE = "UsbDevice[mName=/dev/bus/usb/001/002,mVendorId=1273,mProductId=24745,mClass=0,mSubclass=0,mProtocol=0,mManufacturerName=Brother,mProductName=ADS-1600W,mSerialNumber=G01234567890,mConfigurations=[\nUsbConfiguration[mId=1,mName=null,mAttributes=192,mMaxPower=1,mInterfaces=[\nUsbInterface[mId=0,mAlternateSetting=0,mName=null,mClass=255,mSubclass=255,mProtocol=255,mEndpoints=[\nUsbEndpoint[mAddress=1,mAttributes=2,mMaxPacketSize=512,mInterval=1]\nUsbEndpoint[mAddress=130,mAttributes=2,mMaxPacketSize=512,mInterval=1]]\nUsbInterface[mId=1,mAlternateSetting=0,mName=null,mClass=255,mSubclass=255,mProtocol=255,mEndpoints=[\nUsbEndpoint[mAddress=3,mAttributes=2,mMaxPacketSize=512,mInterval=1]\nUsbEndpoint[mAddress=132,mAttributes=2,mMaxPacketSize=512,mInterval=1]\nUsbEndpoint[mAddress=133,mAttributes=3,mMaxPacketSize=64,mInterval=16]]\nUsbInterface[mId=2,mAlternateSetting=0,mName=null,mClass=255,mSubclass=255,mProtocol=255,mEndpoints=[\nUsbEndpoint[mAddress=6,mAttributes=2,mMaxPacketSize=512,mInterval=1]\nUsbEndpoint[mAddress=135,mAttributes=2,mMaxPacketSize=512,mInterval=1]]]";
    private static final long serialVersionUID = -3516519158293504622L;
    public String mConnectionID = USB_CONNECTION_ID;
    public String mManufacturerName;
    public String mProductID;
    public String mProductName;
    public String mSerialNumber;
    public String mVendorID;

    private BrUsbDevice() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BrUsbDevice)) {
            return false;
        }
        BrUsbDevice brUsbDevice = (BrUsbDevice) obj;
        String str = this.mVendorID;
        if (str != null) {
            if (!str.equals(brUsbDevice.mVendorID)) {
                return false;
            }
        } else if (brUsbDevice.mVendorID != null) {
            return false;
        }
        String str2 = this.mProductID;
        if (str2 != null) {
            if (!str2.equals(brUsbDevice.mProductID)) {
                return false;
            }
        } else if (brUsbDevice.mProductID != null) {
            return false;
        }
        String str3 = this.mSerialNumber;
        if (str3 != null) {
            return str3.equals(brUsbDevice.mSerialNumber);
        }
        return brUsbDevice.mSerialNumber == null;
    }

    public int hashCode() {
        String str = this.mProductID;
        int iHashCode = str != null ? str.hashCode() : 0;
        String str2 = this.mVendorID;
        int iHashCode2 = str2 != null ? str2.hashCode() : 0;
        String str3 = this.mSerialNumber;
        return (iHashCode ^ iHashCode2) ^ (str3 != null ? str3.hashCode() : 0);
    }

    public static BrUsbDevice create(UsbDevice usbDevice) throws Throwable {
        UsbDebug.debug(usbDevice.toString());
        BrUsbDevice brUsbDeviceCreateFromString = createFromString(usbDevice.toString());
        if (brUsbDeviceCreateFromString == null && usbDevice.getVendorId() == BROTHER_VENDOR_ID) {
            String str = BROTHER_USB_MODEL_TABLES.get(Integer.valueOf(usbDevice.getProductId()));
            if (str == null) {
                str = USB_DEFAULT_PRODUCT;
            }
            BrUsbDevice brUsbDevice = new BrUsbDevice();
            brUsbDevice.mManufacturerName = USB_DEFAULT_MANUFACTURER;
            brUsbDevice.mProductID = Integer.toString(usbDevice.getProductId());
            brUsbDevice.mVendorID = Integer.toString(usbDevice.getVendorId());
            brUsbDevice.mProductName = simplyProductName(str);
            brUsbDevice.mSerialNumber = "";
            brUsbDeviceCreateFromString = brUsbDevice;
        }
        if (brUsbDeviceCreateFromString != null && Build.VERSION.SDK_INT > 28) {
            brUsbDeviceCreateFromString.mSerialNumber = "";
        }
        return brUsbDeviceCreateFromString;
    }

    private static BrUsbDevice createFromString(String str) {
        int iIndexOf;
        if (str == null) {
            return null;
        }
        try {
            if (str.length() <= 0 || (iIndexOf = str.indexOf("[")) < 0) {
                return null;
            }
            int i = iIndexOf + 1;
            String[] strArrSplit = str.substring(i, str.indexOf("[", i)).split(",");
            HashMap map = new HashMap();
            for (String str2 : strArrSplit) {
                String[] strArrSplit2 = str2.split("=");
                if (strArrSplit2.length == 2) {
                    map.put(strArrSplit2[0], strArrSplit2[1]);
                }
            }
            BrUsbDevice brUsbDevice = new BrUsbDevice();
            String targetValueWithIgnoredCaseKey = getTargetValueWithIgnoredCaseKey(map, "Manufacturer");
            if (targetValueWithIgnoredCaseKey == null || targetValueWithIgnoredCaseKey.equals(Configurator.NULL)) {
                throw new Exception();
            }
            brUsbDevice.mManufacturerName = targetValueWithIgnoredCaseKey;
            String targetValueWithIgnoredCaseKey2 = getTargetValueWithIgnoredCaseKey(map, "VendorID");
            if (targetValueWithIgnoredCaseKey2 == null || targetValueWithIgnoredCaseKey2.equals(Configurator.NULL)) {
                throw new Exception();
            }
            brUsbDevice.mVendorID = targetValueWithIgnoredCaseKey2;
            String targetValueWithIgnoredCaseKey3 = getTargetValueWithIgnoredCaseKey(map, "ProductID");
            if (targetValueWithIgnoredCaseKey3 == null || targetValueWithIgnoredCaseKey3.equals(Configurator.NULL)) {
                throw new Exception();
            }
            brUsbDevice.mProductID = targetValueWithIgnoredCaseKey3;
            String targetValueWithIgnoredCaseKey4 = getTargetValueWithIgnoredCaseKey(map, "Serial");
            if (targetValueWithIgnoredCaseKey4 == null || targetValueWithIgnoredCaseKey4.equals(Configurator.NULL)) {
                throw new Exception();
            }
            brUsbDevice.mSerialNumber = targetValueWithIgnoredCaseKey4;
            String targetValueWithIgnoredCaseKey5 = getTargetValueWithIgnoredCaseKey(map, "ProductName");
            if (targetValueWithIgnoredCaseKey5 == null || targetValueWithIgnoredCaseKey5.equals(Configurator.NULL)) {
                throw new Exception();
            }
            brUsbDevice.mProductName = simplyProductName(targetValueWithIgnoredCaseKey5);
            return brUsbDevice;
        } catch (Exception unused) {
            return null;
        }
    }

    private static String getTargetValueWithIgnoredCaseKey(Map<String, String> map, String str) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().toLowerCase(Locale.ENGLISH).contains(str.toLowerCase(Locale.ENGLISH))) {
                return entry.getValue();
            }
        }
        return null;
    }

    private static String simplyProductName(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace(SERIES_SUFFIX, "").replace(BROTHER_MODEL_PREFIX, "");
    }
}
