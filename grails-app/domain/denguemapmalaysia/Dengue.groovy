package denguemapmalaysia

import grails.rest.Resource

@Resource(uri='/dengue', formats=['json', 'xml'], readOnly = true)
class Dengue {

    int year
    String province
    int numberOfCases
    String area
    double latitude
    double longitude

    static constraints = {
        year blank:false
        latitude blank:false
        longitude blank:false
        province blank: false
        area blank: false
    }
}
