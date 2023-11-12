(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports'], factory);
  else if (typeof exports === 'object')
    factory(module.exports);
  else
    root['spiragps:webApp'] = factory(typeof this['spiragps:webApp'] === 'undefined' ? {} : this['spiragps:webApp']);
}(this, function (_) {
  'use strict';
  //region block: pre-declaration
  setMetadataFor(Number_0, 'Number', classMeta);
  setMetadataFor(Unit, 'Unit', objectMeta);
  setMetadataFor(Companion, 'Companion', objectMeta);
  setMetadataFor(Long, 'Long', classMeta, Number_0);
  //endregion
  function Number_0() {
  }
  function Unit() {
  }
  var Unit_instance;
  function Unit_getInstance() {
    return Unit_instance;
  }
  function implement(interfaces) {
    var maxSize = 1;
    var masks = [];
    var inductionVariable = 0;
    var last = interfaces.length;
    while (inductionVariable < last) {
      var i = interfaces[inductionVariable];
      inductionVariable = inductionVariable + 1 | 0;
      var currentSize = maxSize;
      var tmp1_elvis_lhs = i.prototype.$imask$;
      var imask = tmp1_elvis_lhs == null ? i.$imask$ : tmp1_elvis_lhs;
      if (!(imask == null)) {
        masks.push(imask);
        currentSize = imask.length;
      }
      var iid = i.$metadata$.iid;
      var tmp;
      if (iid == null) {
        tmp = null;
      } else {
        // Inline function 'kotlin.let' call
        // Inline function 'kotlin.contracts.contract' call
        // Inline function 'kotlin.js.implement.<anonymous>' call
        tmp = bitMaskWith(iid);
      }
      var iidImask = tmp;
      if (!(iidImask == null)) {
        masks.push(iidImask);
        currentSize = Math.max(currentSize, iidImask.length);
      }
      if (currentSize > maxSize) {
        maxSize = currentSize;
      }
    }
    return compositeBitMask(maxSize, masks);
  }
  function bitMaskWith(activeBit) {
    var numberIndex = activeBit >> 5;
    var intArray = new Int32Array(numberIndex + 1 | 0);
    var positionInNumber = activeBit & 31;
    var numberWithSettledBit = 1 << positionInNumber;
    intArray[numberIndex] = intArray[numberIndex] | numberWithSettledBit;
    return intArray;
  }
  function compositeBitMask(capacity, masks) {
    var tmp = 0;
    var tmp_0 = new Int32Array(capacity);
    while (tmp < capacity) {
      var tmp_1 = tmp;
      var result = 0;
      var inductionVariable = 0;
      var last = masks.length;
      while (inductionVariable < last) {
        var mask = masks[inductionVariable];
        inductionVariable = inductionVariable + 1 | 0;
        if (tmp_1 < mask.length) {
          result = result | mask[tmp_1];
        }
      }
      tmp_0[tmp_1] = result;
      tmp = tmp + 1 | 0;
    }
    return tmp_0;
  }
  function protoOf(constructor) {
    return constructor.prototype;
  }
  function defineProp(obj, name, getter, setter) {
    return Object.defineProperty(obj, name, {configurable: true, get: getter, set: setter});
  }
  function objectCreate(proto) {
    return Object.create(proto);
  }
  function Companion() {
    Companion_instance = this;
    this.a_1 = new Long(0, -2147483648);
    this.b_1 = new Long(-1, 2147483647);
    this.c_1 = 8;
    this.d_1 = 64;
  }
  var Companion_instance;
  function Companion_getInstance() {
    if (Companion_instance == null)
      new Companion();
    return Companion_instance;
  }
  function Long(low, high) {
    Companion_getInstance();
    Number_0.call(this);
    this.e_1 = low;
    this.f_1 = high;
  }
  protoOf(Long).g = function () {
    return toNumber(this);
  };
  protoOf(Long).valueOf = function () {
    return this.g();
  };
  var ZERO;
  var ONE;
  var NEG_ONE;
  var MAX_VALUE;
  var MIN_VALUE;
  var TWO_PWR_24_;
  function toNumber(_this__u8e3s4) {
    _init_properties_longjs_kt__tqrzid();
    return _this__u8e3s4.f_1 * 4.294967296E9 + getLowBitsUnsigned(_this__u8e3s4);
  }
  function fromInt(value) {
    _init_properties_longjs_kt__tqrzid();
    return new Long(value, value < 0 ? -1 : 0);
  }
  function getLowBitsUnsigned(_this__u8e3s4) {
    _init_properties_longjs_kt__tqrzid();
    return _this__u8e3s4.e_1 >= 0 ? _this__u8e3s4.e_1 : 4.294967296E9 + _this__u8e3s4.e_1;
  }
  var properties_initialized_longjs_kt_5aju7t;
  function _init_properties_longjs_kt__tqrzid() {
    if (!properties_initialized_longjs_kt_5aju7t) {
      properties_initialized_longjs_kt_5aju7t = true;
      ZERO = fromInt(0);
      ONE = fromInt(1);
      NEG_ONE = fromInt(-1);
      MAX_VALUE = new Long(-1, 2147483647);
      MIN_VALUE = new Long(0, -2147483648);
      TWO_PWR_24_ = fromInt(16777216);
    }
  }
  function classMeta(name, defaultConstructor, associatedObjectKey, associatedObjects, suspendArity) {
    return createMetadata('class', name, defaultConstructor, associatedObjectKey, associatedObjects, suspendArity, null);
  }
  function createMetadata(kind, name, defaultConstructor, associatedObjectKey, associatedObjects, suspendArity, iid) {
    var undef = VOID;
    return {kind: kind, simpleName: name, associatedObjectKey: associatedObjectKey, associatedObjects: associatedObjects, suspendArity: suspendArity, $kClass$: undef, defaultConstructor: defaultConstructor, iid: iid};
  }
  function setMetadataFor(ctor, name, metadataConstructor, parent, interfaces, defaultConstructor, associatedObjectKey, associatedObjects, suspendArity) {
    if (!(parent == null)) {
      ctor.prototype = Object.create(parent.prototype);
      ctor.prototype.constructor = ctor;
    }
    var metadata = metadataConstructor(name, defaultConstructor, associatedObjectKey, associatedObjects, suspendArity == null ? [] : suspendArity);
    ctor.$metadata$ = metadata;
    if (!(interfaces == null)) {
      var receiver = !(metadata.iid == null) ? ctor : ctor.prototype;
      receiver.$imask$ = implement(interfaces);
    }
  }
  function objectMeta(name, defaultConstructor, associatedObjectKey, associatedObjects, suspendArity) {
    return createMetadata('object', name, defaultConstructor, associatedObjectKey, associatedObjects, suspendArity, null);
  }
  function get_VOID() {
    _init_properties_void_kt__3zg9as();
    return VOID;
  }
  var VOID;
  var properties_initialized_void_kt_e4ret2;
  function _init_properties_void_kt__3zg9as() {
    if (!properties_initialized_void_kt_e4ret2) {
      properties_initialized_void_kt_e4ret2 = true;
      VOID = void 0;
    }
  }
  var MaxSupportedRadix;
  function _createLocalCallbackScope$accessor$wmqves() {
    return _createLocalCallbackScope();
  }
  function _releaseLocalCallbackScope$accessor$wmqves() {
    return _releaseLocalCallbackScope();
  }
  var onContentScaleChanged;
  var DefaultCacheSize;
  var DefaultCacheSize_0;
  var defaultCanvasElementId;
  var UNSET;
  var platformFlingScrollFriction;
  var SNAPSHOTS_INTERVAL_MILLIS;
  var TapIndicationDelay;
  var isInTouchMode;
  var _accountBox;
  var _accountCircle;
  var _add;
  var _addCircle;
  var _arrowBack;
  var _arrowDropDown;
  var _arrowForward;
  var _build;
  var _call;
  var _check;
  var _checkCircle;
  var _clear;
  var _close;
  var _create;
  var _dateRange;
  var _delete;
  var _done;
  var _edit;
  var _email;
  var _exitToApp;
  var _face;
  var _favorite;
  var _favoriteBorder;
  var _home;
  var _info;
  var _keyboardArrowDown;
  var _keyboardArrowLeft;
  var _keyboardArrowRight;
  var _keyboardArrowUp;
  var _list;
  var _locationOn;
  var _lock;
  var _mailOutline;
  var _menu;
  var _moreVert;
  var _notifications;
  var _person;
  var _phone;
  var _place;
  var _playArrow;
  var _refresh;
  var _search;
  var _send;
  var _settings;
  var _share;
  var _shoppingCart;
  var _star;
  var _thumbUp;
  var _warning;
  var _accountBox_0;
  var _accountCircle_0;
  var _add_0;
  var _addCircle_0;
  var _arrowBack_0;
  var _arrowDropDown_0;
  var _arrowForward_0;
  var _build_0;
  var _call_0;
  var _check_0;
  var _checkCircle_0;
  var _clear_0;
  var _close_0;
  var _create_0;
  var _dateRange_0;
  var _delete_0;
  var _done_0;
  var _edit_0;
  var _email_0;
  var _exitToApp_0;
  var _face_0;
  var _favorite_0;
  var _favoriteBorder_0;
  var _home_0;
  var _info_0;
  var _keyboardArrowDown_0;
  var _keyboardArrowLeft_0;
  var _keyboardArrowRight_0;
  var _keyboardArrowUp_0;
  var _list_0;
  var _locationOn_0;
  var _lock_0;
  var _mailOutline_0;
  var _menu_0;
  var _moreVert_0;
  var _notifications_0;
  var _person_0;
  var _phone_0;
  var _place_0;
  var _playArrow_0;
  var _refresh_0;
  var _search_0;
  var _send_0;
  var _settings_0;
  var _share_0;
  var _shoppingCart_0;
  var _star_0;
  var _thumbUp_0;
  var _warning_0;
  var _accountBox_1;
  var _accountCircle_1;
  var _add_1;
  var _addCircle_1;
  var _arrowBack_1;
  var _arrowDropDown_1;
  var _arrowForward_1;
  var _build_1;
  var _call_1;
  var _check_1;
  var _checkCircle_1;
  var _clear_1;
  var _close_1;
  var _create_1;
  var _dateRange_1;
  var _delete_1;
  var _done_1;
  var _edit_1;
  var _email_1;
  var _exitToApp_1;
  var _face_1;
  var _favorite_1;
  var _favoriteBorder_1;
  var _home_1;
  var _info_1;
  var _keyboardArrowDown_1;
  var _keyboardArrowLeft_1;
  var _keyboardArrowRight_1;
  var _keyboardArrowUp_1;
  var _list_1;
  var _locationOn_1;
  var _lock_1;
  var _mailOutline_1;
  var _menu_1;
  var _moreVert_1;
  var _notifications_1;
  var _person_1;
  var _phone_1;
  var _place_1;
  var _playArrow_1;
  var _refresh_1;
  var _search_1;
  var _send_1;
  var _settings_1;
  var _share_1;
  var _shoppingCart_1;
  var _star_1;
  var _thumbUp_1;
  var _warning_1;
  var _accountBox_2;
  var _accountCircle_2;
  var _add_2;
  var _addCircle_2;
  var _arrowBack_2;
  var _arrowDropDown_2;
  var _arrowForward_2;
  var _build_2;
  var _call_2;
  var _check_2;
  var _checkCircle_2;
  var _clear_2;
  var _close_2;
  var _create_2;
  var _dateRange_2;
  var _delete_2;
  var _done_2;
  var _edit_2;
  var _email_2;
  var _exitToApp_2;
  var _face_2;
  var _favorite_2;
  var _favoriteBorder_2;
  var _home_2;
  var _info_2;
  var _keyboardArrowDown_2;
  var _keyboardArrowLeft_2;
  var _keyboardArrowRight_2;
  var _keyboardArrowUp_2;
  var _list_2;
  var _locationOn_2;
  var _lock_2;
  var _mailOutline_2;
  var _menu_2;
  var _moreVert_2;
  var _notifications_2;
  var _person_2;
  var _phone_2;
  var _place_2;
  var _playArrow_2;
  var _refresh_2;
  var _search_2;
  var _send_2;
  var _settings_2;
  var _share_2;
  var _shoppingCart_2;
  var _star_2;
  var _thumbUp_2;
  var _warning_2;
  var _accountBox_3;
  var _accountCircle_3;
  var _add_3;
  var _addCircle_3;
  var _arrowBack_3;
  var _arrowDropDown_3;
  var _arrowForward_3;
  var _build_3;
  var _call_3;
  var _check_3;
  var _checkCircle_3;
  var _clear_3;
  var _close_3;
  var _create_3;
  var _dateRange_3;
  var _delete_3;
  var _done_3;
  var _edit_3;
  var _email_3;
  var _exitToApp_3;
  var _face_3;
  var _favorite_3;
  var _favoriteBorder_3;
  var _home_3;
  var _info_3;
  var _keyboardArrowDown_3;
  var _keyboardArrowLeft_3;
  var _keyboardArrowRight_3;
  var _keyboardArrowUp_3;
  var _list_3;
  var _locationOn_3;
  var _lock_3;
  var _mailOutline_3;
  var _menu_3;
  var _moreVert_3;
  var _notifications_3;
  var _person_3;
  var _phone_3;
  var _place_3;
  var _playArrow_3;
  var _refresh_3;
  var _search_3;
  var _send_3;
  var _settings_3;
  var _share_3;
  var _shoppingCart_3;
  var _star_3;
  var _thumbUp_3;
  var _warning_3;
  var PACKET_MAX_COPY_SIZE;
  var DISABLE_SFG;
  //region block: init
  Unit_instance = new Unit();
  MaxSupportedRadix = 36;
  onContentScaleChanged = null;
  DefaultCacheSize = 8;
  DefaultCacheSize_0 = 8;
  defaultCanvasElementId = 'ComposeTarget';
  UNSET = 3.4028235E38;
  platformFlingScrollFriction = 0.015;
  SNAPSHOTS_INTERVAL_MILLIS = 5000;
  TapIndicationDelay = new Long(0, 0);
  isInTouchMode = false;
  _accountBox = null;
  _accountCircle = null;
  _add = null;
  _addCircle = null;
  _arrowBack = null;
  _arrowDropDown = null;
  _arrowForward = null;
  _build = null;
  _call = null;
  _check = null;
  _checkCircle = null;
  _clear = null;
  _close = null;
  _create = null;
  _dateRange = null;
  _delete = null;
  _done = null;
  _edit = null;
  _email = null;
  _exitToApp = null;
  _face = null;
  _favorite = null;
  _favoriteBorder = null;
  _home = null;
  _info = null;
  _keyboardArrowDown = null;
  _keyboardArrowLeft = null;
  _keyboardArrowRight = null;
  _keyboardArrowUp = null;
  _list = null;
  _locationOn = null;
  _lock = null;
  _mailOutline = null;
  _menu = null;
  _moreVert = null;
  _notifications = null;
  _person = null;
  _phone = null;
  _place = null;
  _playArrow = null;
  _refresh = null;
  _search = null;
  _send = null;
  _settings = null;
  _share = null;
  _shoppingCart = null;
  _star = null;
  _thumbUp = null;
  _warning = null;
  _accountBox_0 = null;
  _accountCircle_0 = null;
  _add_0 = null;
  _addCircle_0 = null;
  _arrowBack_0 = null;
  _arrowDropDown_0 = null;
  _arrowForward_0 = null;
  _build_0 = null;
  _call_0 = null;
  _check_0 = null;
  _checkCircle_0 = null;
  _clear_0 = null;
  _close_0 = null;
  _create_0 = null;
  _dateRange_0 = null;
  _delete_0 = null;
  _done_0 = null;
  _edit_0 = null;
  _email_0 = null;
  _exitToApp_0 = null;
  _face_0 = null;
  _favorite_0 = null;
  _favoriteBorder_0 = null;
  _home_0 = null;
  _info_0 = null;
  _keyboardArrowDown_0 = null;
  _keyboardArrowLeft_0 = null;
  _keyboardArrowRight_0 = null;
  _keyboardArrowUp_0 = null;
  _list_0 = null;
  _locationOn_0 = null;
  _lock_0 = null;
  _mailOutline_0 = null;
  _menu_0 = null;
  _moreVert_0 = null;
  _notifications_0 = null;
  _person_0 = null;
  _phone_0 = null;
  _place_0 = null;
  _playArrow_0 = null;
  _refresh_0 = null;
  _search_0 = null;
  _send_0 = null;
  _settings_0 = null;
  _share_0 = null;
  _shoppingCart_0 = null;
  _star_0 = null;
  _thumbUp_0 = null;
  _warning_0 = null;
  _accountBox_1 = null;
  _accountCircle_1 = null;
  _add_1 = null;
  _addCircle_1 = null;
  _arrowBack_1 = null;
  _arrowDropDown_1 = null;
  _arrowForward_1 = null;
  _build_1 = null;
  _call_1 = null;
  _check_1 = null;
  _checkCircle_1 = null;
  _clear_1 = null;
  _close_1 = null;
  _create_1 = null;
  _dateRange_1 = null;
  _delete_1 = null;
  _done_1 = null;
  _edit_1 = null;
  _email_1 = null;
  _exitToApp_1 = null;
  _face_1 = null;
  _favorite_1 = null;
  _favoriteBorder_1 = null;
  _home_1 = null;
  _info_1 = null;
  _keyboardArrowDown_1 = null;
  _keyboardArrowLeft_1 = null;
  _keyboardArrowRight_1 = null;
  _keyboardArrowUp_1 = null;
  _list_1 = null;
  _locationOn_1 = null;
  _lock_1 = null;
  _mailOutline_1 = null;
  _menu_1 = null;
  _moreVert_1 = null;
  _notifications_1 = null;
  _person_1 = null;
  _phone_1 = null;
  _place_1 = null;
  _playArrow_1 = null;
  _refresh_1 = null;
  _search_1 = null;
  _send_1 = null;
  _settings_1 = null;
  _share_1 = null;
  _shoppingCart_1 = null;
  _star_1 = null;
  _thumbUp_1 = null;
  _warning_1 = null;
  _accountBox_2 = null;
  _accountCircle_2 = null;
  _add_2 = null;
  _addCircle_2 = null;
  _arrowBack_2 = null;
  _arrowDropDown_2 = null;
  _arrowForward_2 = null;
  _build_2 = null;
  _call_2 = null;
  _check_2 = null;
  _checkCircle_2 = null;
  _clear_2 = null;
  _close_2 = null;
  _create_2 = null;
  _dateRange_2 = null;
  _delete_2 = null;
  _done_2 = null;
  _edit_2 = null;
  _email_2 = null;
  _exitToApp_2 = null;
  _face_2 = null;
  _favorite_2 = null;
  _favoriteBorder_2 = null;
  _home_2 = null;
  _info_2 = null;
  _keyboardArrowDown_2 = null;
  _keyboardArrowLeft_2 = null;
  _keyboardArrowRight_2 = null;
  _keyboardArrowUp_2 = null;
  _list_2 = null;
  _locationOn_2 = null;
  _lock_2 = null;
  _mailOutline_2 = null;
  _menu_2 = null;
  _moreVert_2 = null;
  _notifications_2 = null;
  _person_2 = null;
  _phone_2 = null;
  _place_2 = null;
  _playArrow_2 = null;
  _refresh_2 = null;
  _search_2 = null;
  _send_2 = null;
  _settings_2 = null;
  _share_2 = null;
  _shoppingCart_2 = null;
  _star_2 = null;
  _thumbUp_2 = null;
  _warning_2 = null;
  _accountBox_3 = null;
  _accountCircle_3 = null;
  _add_3 = null;
  _addCircle_3 = null;
  _arrowBack_3 = null;
  _arrowDropDown_3 = null;
  _arrowForward_3 = null;
  _build_3 = null;
  _call_3 = null;
  _check_3 = null;
  _checkCircle_3 = null;
  _clear_3 = null;
  _close_3 = null;
  _create_3 = null;
  _dateRange_3 = null;
  _delete_3 = null;
  _done_3 = null;
  _edit_3 = null;
  _email_3 = null;
  _exitToApp_3 = null;
  _face_3 = null;
  _favorite_3 = null;
  _favoriteBorder_3 = null;
  _home_3 = null;
  _info_3 = null;
  _keyboardArrowDown_3 = null;
  _keyboardArrowLeft_3 = null;
  _keyboardArrowRight_3 = null;
  _keyboardArrowUp_3 = null;
  _list_3 = null;
  _locationOn_3 = null;
  _lock_3 = null;
  _mailOutline_3 = null;
  _menu_3 = null;
  _moreVert_3 = null;
  _notifications_3 = null;
  _person_3 = null;
  _phone_3 = null;
  _place_3 = null;
  _playArrow_3 = null;
  _refresh_3 = null;
  _search_3 = null;
  _send_3 = null;
  _settings_3 = null;
  _share_3 = null;
  _shoppingCart_3 = null;
  _star_3 = null;
  _thumbUp_3 = null;
  _warning_3 = null;
  PACKET_MAX_COPY_SIZE = 200;
  DISABLE_SFG = false;
  //endregion
  return _;
}));

//# sourceMappingURL=spiragps.js.map
