package denguemapmalaysia

import groovy.transform.ToString

@ToString
class Dengue {

    int year
    String province
    int numberOfCases
    String area
    double latitude
    double longitude
    String address
    int week

    static constraints = {
        year blank:false
        latitude blank:false
        longitude blank:false
        province blank: false
        area blank: false
        address blank: false
        week min: 1, max: 52
    }

    static mapping = {
      autoTimestamp true
    }
}
