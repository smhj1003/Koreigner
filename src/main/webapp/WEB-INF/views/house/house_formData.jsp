<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h1>House Info</h1>
<hr>

<input type="hidden" name="mem_email" value="${mem_id }">
<table id="houseInfo">
	<tr>
		<th>TITLE</th>
		<td colspan="3">
			<div class="td_div">
				<input type="text" name="subject" size="30" style="width: 90%;">
			</div>
		</td>
	</tr>
	<tr>
		<th>Buliding Type</th>
		<td style="width: 40%">
			<div class="td_div">
				<input type="radio" value="0" name="build_type"> villa <input
					type="radio" value="1" name="build_type" style="margin-left: 15px">
				Goshiwon <input type="radio" value="2" name="build_type"
					style="margin-left: 15px"> Semi-Officetel
			</div>
		</td>
		<th>Room Type</th>
		<td style="width: 40%">
			<div class="td_div">
				<input type="radio" value="0" name="room_type"> Studio <input
					type="radio" value="1" name="room_type" style="margin-left: 15px">
				Two <input type="radio" value="2" name="room_type"
					style="margin-left: 15px"> Three+
			</div>
		</td>
	</tr>
	<tr>
		<th>Loctation</th>
		<td colspan="3">
			<div class="td_div">
				<label style="width: 30%">RoadAddress:</label> <input type="text"
					id="roadAddr" name="address" readonly="readonly"
					style="width: 60%; margin-left: 5px;"> <input type="button"
					value="Search" onclick="goPopup();" style="width: 10%">
			</div>
			<div class="td_div">
				<label style="width: 30%">DetailAddress:</label> <input type="text"
					id="addrDetail" name="address_detail" readonly="readonly"
					style="width: 71%;">
			</div> <input type="hidden" id="siNm" name="do_en" title="주소" value="">
			<input type="hidden" id="sggNm" name="gu_gun_eup_eng" title="주소"
			value="">
			<div id="mapDisp" style="border: 1px solid black;"></div>
		</td>
	</tr>
	<tr>
		<th>Area</th>
		<td style="width: 40%">
			<div class="td_div">
				<label style="width: 70%">Square Meter: </label> <input
					type="number" id="" name="room_area" step="1"
					min="1" value='1'>
			</div>
		</td>
		<th>Floor</th>
		<td style="width: 40%">
			<div class="td_div">
				<label style="width: 20%">Buliding: </label> <input type="number"
					id="" name="bulid_layers"  step="1" min="1"
					value='1'> <label style="width: 20%; margin-left: 10px;">Floor:
				</label> <input type="number" id="" name="floor_layers" 
					step="1" min="-3" value='1'>
			</div>
			<div class="td_div"></div>
			<div class="td_div">
				<input type="radio" id="floorNomal" value="0" name="floor_type"
					checked="checked"> Nomal <input type="radio"
					id="floorRoofTops" value="1" name="floor_type"
					style="margin-left: 15px"> RoofTops <input type="radio"
					id="floorSemi-basement" value="2" name="floor_type"
					style="margin-left: 15px"> Semi-basement
			</div>
		</td>
	</tr>
	<tr>
		<th>Available Days</th>
		<td>
			<div class="td_div">
				<input type="radio" id="radio_available_date_Imme" value="0"
					name="radio_available_date"> Immediately
			</div>
			<div class="td_div">
				<input type="radio" id="radio_available_date_After" value="1"
					name="radio_available_date"> After this <input type="text"
					id="datepicker" readonly="readonly">

			</div> <input type="hidden" id="current_date" name="current_date">
			<input type="hidden" id="available_date" name="available_date">

		</td>
		<th>Itinerary</th>
		<td>
			<div class="td_div">
				<select id="stay_num_min" name="stay_num_min" class="">
					<option value="1">1 month</option>
					<option value="2">2 months</option>
					<option value="3">3 months</option>
					<option value="4">4 months</option>
					<option value="5">5 months</option>
					<option value="6">6 months</option>
					<option value="7">7 months</option>
					<option value="8">8 months</option>
					<option value="9">9 months</option>
					<option value="10">10 months</option>
					<option value="11">11 months</option>
					<option value="12" selected>1 year</option>
					<option value="13">2 years</option>
				</select>
			</div>


		</td>
	</tr>

</table>
<!-- ------------------------------------------------------------------------------------------------------------------- -->
<h1>Transaction Information</h1>
<hr>
<table id="transactionInfo">
	<colgroup>
		<col>
		<col width="195px">
	</colgroup>
	<tr>
		<th rowspan="3">Pricing<br>Information
		</th>
		<td class="borderRight-none">
			<div class="td_div">Deposit</div>
		</td>
		<td class="borderLeft-none">
			<div class="td_div">
				<input type="text" id="deposit" class="" name="deposit" size="30"
					onkeyup="inputNumberAutoComma(this)" value='0'><br>
			</div>
		</td>
	</tr>
	<tr>
		<td class="borderRight-none">
			<div class="td_div">Monthly rent</div>
		</td>
		<td class="borderLeft-none">
			<div class="td_div">
				<input type="text" id="monthly_rent" class=" " name="monthly_rent"
					size="30" onkeyup="inputNumberAutoComma(this)" value='0'><br>
			</div>
		</td>
	</tr>
	<tr>
		<td class="borderRight-none">
			<div class="td_div">Management expense</div>
		</td>
		<td class="borderLeft-none">
			<div class="td_div">
				<input type="text" id="text_management_expense" class=" "
					name="text_management_expense" size="30"
					onkeyup="inputNumberAutoComma(this)"> <input
					type="checkbox" value="0" name="chk_management_expense">none<br>
			</div> <input type="hidden" id="management_expense"
			name="management_expense">
		</td>
	</tr>

</table>

<!-- ------------------------------------------------------------------------------------------------------------------- -->
<h1>Facilities information</h1>
<hr>
<table id="facilitiesInfo">
	<tr>
		<th>Cooling and Heating</th>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="air_conditioner" value="1"> Air conditioner</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="heating" value="1"> Heater</td>
		<td colspan="3"></td>

	</tr>
	<tr>
		<th rowspan="2">Living facility</th>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="closet" value="1"> Closet</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="desk" value="1"> Desk</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="chair" value="1"> Chair</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="bad_type" value="1"> bed</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="refrigerator" value="1"> Refrigerator</td>

	</tr>
	<tr>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="laundry" value="1"> Laundry</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="kitchen_stove" value="1"> Kitchen_stove</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="sink" value="1"> Sink</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="bathroom" value="1"> Bathroom</td>
		<td></td>


	</tr>
	<tr>
		<th>Ohter</th>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="internet" value="1"> Internet</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="wifi" value="1"> Wifi</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="elevator" value="1"> Elevator</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="pet" value="1"> Pet</td>
		<td class="td_option"><input type="checkbox" class="chk_option"
			name="parking" value="1"> Parking</td>
	</tr>
</table>

<h1>Room Image</h1>
<hr>
<table id="imageInfo">
	<tr>
		<th>Image Upload</th>
		<td>
			<div class="td_div">
				<input type="file" id="file" name="file" multiple>
			</div>
		</td>
	</tr>
</table>
