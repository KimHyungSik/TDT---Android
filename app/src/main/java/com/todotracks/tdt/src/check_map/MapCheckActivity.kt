package com.todotracks.tdt.src.check_map

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.todotracks.tdt.kotlin.config.BaseActivity
import com.todotracks.tdt.databinding.ActivityMapBinding
import com.todotracks.tdt.databinding.ActivityMapCheckBinding
import java.io.IOException
import java.util.*
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.NaverMapOptions


class MapCheckActivity : BaseActivity<ActivityMapCheckBinding>(ActivityMapCheckBinding::inflate),
    OnMapReadyCallback,
    NaverMap.OnCameraChangeListener, NaverMap.OnCameraIdleListener {
    lateinit private var mapView: MapView
    lateinit private var locationSource: FusedLocationSource
    lateinit private var naverMap: NaverMap

    var mnaverMap: NaverMap? = null
    private var mIsCameraAnimated = false
    var mTvPm10: TextView? = null
    var mSido: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapView = binding.mapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        val cameraPosition = CameraPosition(
            LatLng(37.50133795399799, 127.02662695566022),  // 대상 지점
            16.0,  // 줌 레벨
            0.0,  // 기울임 각도
            180.0 // 베어링 각도
        )
        val options = NaverMapOptions()
            .camera(cameraPosition)

        // 카메라가 멈출시


//         카메라 위치 이동후 경도, 위도
//        binding.registerBtn.setOnClickListener {
//            var cameraPosition = naverMap.cameraPosition
////            var intent = Intent(this, LocationDetailActivity::class.java)
////            intent.putExtra("location", binding.locMainTxt.text)
////            intent.putExtra("latitude", cameraPosition.target.latitude.toString())
////            intent.putExtra("longitude", cameraPosition.target.longitude.toString())
//            Log.d("position!!","대상 지점 위도: " + cameraPosition.target.latitude + ", " +
//                    "대상 지점 경도: " + cameraPosition.target.longitude )
////            startActivity(intent)
//        }

//        binding.saveBtn.setOnClickListener {
//            var cameraPosition = naverMap.cameraPosition
//            var address = getAddress(this, cameraPosition.target.latitude, cameraPosition.target.longitude)
//            val intent = Intent() //startActivity()를 할것이 아니므로 그냥 빈 인텐트로 만듦
//            var data = address
//            intent.putExtra("address", data)
//            setResult(RESULT_OK, intent)
//            finish()
//        }

    }

    //위치정보 권한 설정
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        if (locationSource!!.onRequestPermissionsResult(
                requestCode, permissions, grantResults
            )
        ) {
            return
        }
        super.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        val uiSettings = naverMap.uiSettings
        uiSettings.isCompassEnabled = true // 나침반
        uiSettings.isScaleBarEnabled = true // 거리
        uiSettings.isZoomControlEnabled = true // 줌
        uiSettings.isLocationButtonEnabled = true // 내가 있는곳

        // 지도상에 마커 표시
        val marker = Marker()
        marker.position = LatLng(37.50133795399799, 127.02662695566022)
//        marker.tag = "강남점 : 서울 강남구 강남대로 432 점프밀라노 5층"
        marker.map = naverMap

        val cameraPosition = CameraPosition(
            marker.position,  // 대상 지점
            16.0,  // 줌 레벨
            0.0,  // 기울임 각도
            180.0 // 베어링 각도
        )

        //맵 위치 변경시 리스너
        naverMap.addOnCameraChangeListener(this)
        naverMap.addOnCameraIdleListener(this)

//        var cameraPosition = naverMap.cameraPosition

        naverMap.addOnCameraIdleListener {
            var address = getAddress(this, cameraPosition.target.latitude, cameraPosition.target.longitude)
//            showCustomToast("현재 주소 : "+address)
        }

    }

    //지도가 이동시에 이동중임을 확인
    override fun onCameraChange(reason: Int, animated: Boolean) {
        mIsCameraAnimated = animated
//        binding.mark.setImageResource(R.drawable.ic_map_moving_preview_rev_1)
    }

    //지도가 멈춘 위치의 좌표로 API로 URL post
    override fun onCameraIdle() {
        if (mIsCameraAnimated) {
//            binding.mark.setImageResource(R.drawable.ic_map_mark_adobespark2)
//            val cameraPosition = naverMap.getCameraPosition()
//            var address = getAddress(this, cameraPosition.target.latitude, cameraPosition.target.longitude)
//            showCustomToast("현재 주소 : "+address)
//            binding.locMainTxt.text = address
//            binding.locSubTxt.text = address
//            binding.locMainTxt.setText(address.toString())
//            binding.locSubTxt.setText(address.toString())
        }
    }

    fun getAddress(mContext: Context?, lat: Double, lng: Double): String? {
        var nowAddr = "현재 위치를 확인 할 수 없습니다."
        val geocoder = Geocoder(mContext, Locale.KOREA)
        val address: List<Address>?
        try {
            if (geocoder != null) {
                // 한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받고
                // 세번째 파라메터인 maxResults는 리턴받을 주소의 최대 갯수를 지정함
                // (여기서는 1개만 받는걸로...)
                address = geocoder.getFromLocation(lat, lng, 1)
                if (address != null && address.size > 0) {
                    // 주소 받아오기
                    nowAddr = address[0].getAddressLine(0).toString()
                }
            }
        } catch (e: IOException) {
            Toast.makeText(mContext, "주소를 가져 올 수 없습니다.", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
        return nowAddr
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}
