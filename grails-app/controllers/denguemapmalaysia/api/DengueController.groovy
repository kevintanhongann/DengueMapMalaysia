import denguemapmalaysia.Dengue
import denguemapmalaysia.DengueResponse
import grails.converters.JSON

class DengueController {

    def all() {
        def data = Dengue.list()
        render data as JSON
    }

    def showByWeek() {
        def week = params['week']
        def dengueResponse
        if(week){
            def data = Dengue.findByWeek(week)
            dengueResponse = new DengueResponse(data: data, success: true, message: '')
        }else{
            dengueResponse = new DengueResponse(data: null, success: false, message: 'not found')
        }

        render dengueResponse as JSON
    }

    def showByState(){

    }


}