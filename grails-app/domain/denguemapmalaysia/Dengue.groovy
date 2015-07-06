package denguemapmalaysia

import grails.rest.Resource
import groovy.transform.ToString

@Resource(uri='/api/dengue', formats=['json', 'xml'], readOnly = true)
@ToString
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

    static mapping = {
      autoTimeStamp true
    }
}
