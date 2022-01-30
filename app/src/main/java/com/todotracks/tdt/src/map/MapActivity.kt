package com.todotracks.tdt.src.map

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.FusedLocationSource
import com.todotracks.tdt.databinding.ActivityMapBinding
import com.todotracks.tdt.kotlin.config.BaseActivity
import com.todotracks.tdt.src.map.model.PostSubRequest
import com.todotracks.tdt.src.map.model.SearchResponse
import com.todotracks.tdt.src.map.service.PostSubService
import com.todotracks.tdt.src.map.service.PostSubView
import com.todotracks.tdt.src.map.service.SearchService
import com.todotracks.tdt.src.map.service.SearchView
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MapActivity : BaseActivity<ActivityMapBinding>(ActivityMapBinding::inflate),
    OnMapReadyCallback,
    NaverMap.OnCameraChangeListener, NaverMap.OnCameraIdleListener, SearchView, PostSubView {
    lateinit private var mapView: MapView
    lateinit private var locationSource: FusedLocationSource
    lateinit private var naverMap: NaverMap
    private var title : String? = null
    private var date : String? = null
    private var mainId : Int? = null

    var mnaverMap: NaverMap? = null
    private var mIsCameraAnimated = false
    var mTvPm10: TextView? = null
    var mSido: String? = null

    var search_list = ArrayList<String>()
    var list = ArrayList<LatLng>()

    // 지도상에 마커 표시
    var marker1 = Marker()
    var marker2 = Marker()
    var marker3 = Marker()
    var marker4 = Marker()
    var marker5 = Marker()

    // result marker
    var marker = Marker()

    // InfoWindow
    private var infoWindow: InfoWindow? = InfoWindow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapView = binding.mapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        //엔터키 이벤트 처리
        binding.search.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                //Enter key Action
                if (event.getAction() === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    var search = binding.search.text.toString()
                    SearchService(this@MapActivity).tryGetSearch(search)
                    return true
                }
                return false
            }
        })

        binding.searchBtn.setOnClickListener {
            var search: String = binding.search.text.toString()
            if(search != null){
                SearchService(this).tryGetSearch(search)
            }

//            startActivity(Intent(this, MapCheckActivity::class.java))
        }

        val extras = intent.extras
        extras?.let {
            title = extras.getString("title")
            date = extras.getString("date")
            mainId = extras.getInt("mainId")
        }

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

        binding.backBtn.setOnClickListener {
            finish()
        }
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

        //맵 위치 변경시 리스너
        naverMap.addOnCameraChangeListener(this)
        naverMap.addOnCameraIdleListener(this)

        var cameraPosition = naverMap.cameraPosition
        naverMap.addOnCameraIdleListener {
//            var address = getAddress(this, cameraPosition.target.latitude, cameraPosition.target.longitude)
//            showCustomToast("현재 주소 : "+address)
//            binding.locMainTxt.setText(address)
//            binding.locSubTxt.setText(address)
        }

        if(list.size != 0){
            marker1.map = naverMap
            marker1.onClickListener = Overlay.OnClickListener {
                infoWindow_present(marker1)
                false
            }

            marker2.map = naverMap
            marker2.onClickListener = Overlay.OnClickListener {
                infoWindow_present(marker2)
                false
            }

            marker3.map = naverMap
            marker3.onClickListener = Overlay.OnClickListener {
                infoWindow_present(marker3)
                false
            }

            marker4.map = naverMap
            marker4.onClickListener = Overlay.OnClickListener {
                infoWindow_present(marker4)
                false
            }

            marker5.map = naverMap
            marker5.onClickListener = Overlay.OnClickListener {
                infoWindow_present(marker5)
                false
            }
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

    fun infoWindow_present(maker: Marker) {
        infoWindow!!.setAdapter(object : InfoWindow.DefaultTextAdapter(application) {
            override fun getText(infoWindow: InfoWindow): CharSequence {
                return "이 위치로 하기"
            }
        })

        //인포창의 우선순위
        infoWindow!!.setZIndex(10)
        //투명도 조정
        infoWindow!!.setAlpha(0.9f)
        //인포창 표시
        infoWindow!!.open(maker)

        infoWindow!!.setOnClickListener(Overlay.OnClickListener {
            marker = maker
            Log.d("result_address", "result")
            save_address()
            false
        })
    }

    fun save_address(){
        var text = getSharedPreferences("tdt", MODE_PRIVATE)
        var editor = text.edit()
        var address = getAddress(this, marker.position.latitude, marker.position.longitude)
//        finish()

        if(title != null && date != null && mainId != null){
            val reqest = PostSubRequest(mainId!!, title!!,
                date!!+"T01:00", "",marker.position.latitude, marker.position.longitude, address)
            PostSubService(this).tryPostSub(reqest)
        }
    }

    override fun onGetSearchSuccess(response: SearchResponse) {
        search_list.clear()
        list.clear()
        if(response.items.size != 0){
            for(i in 0..response.items.size-1){
                search_list.add(response.items.get(i).address!!)
            }

            for(i in 0..search_list.size-1){
                var result = Geocoder(this).getFromLocationName(search_list.get(i), 1)
                var resultGeo = LatLng(result.get(0).latitude, result.get(0).longitude)
                list.add(resultGeo)
            }

//        showCustomToast(list.toString())
            marker1.position = list.get(0)
            marker2.position = list.get(1)
            marker3.position = list.get(2)
            marker4.position = list.get(3)
            marker5.position = list.get(4)
        }
        onMapReady(naverMap)
    }

    override fun onGetSearchFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPostSubSuccess(response: String?) {
        finish()
    }

    override fun onPostSubFailure(message: String) {
        finish()
    }
}
