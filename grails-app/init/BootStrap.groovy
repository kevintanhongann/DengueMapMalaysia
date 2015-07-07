import denguemapmalaysia.Dengue
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
        //TODO pump in existing data from the spreadsheet into the database
        //TODO use POI Apache to read from the spreadsheet
        def dengue = new Dengue(area: 'test area', latitude: 0.1231, longitude: 0.12321, numberOfCases: 11, year: 2012, province: 'Shah Alam', address: 'askldsadljskdjadladljdalsjd', week: 1)
        dengue.save()



        JSON.registerObjectMarshaller(Dengue){
            return [
                year: it.year,
                address: it.address,
                week: it.week,
                numberOfCases: it.numberOfCases,
                province: it.province,
                latitude: it.latitude,
                longitude: it.longitude,
                area: it.area
            ]
        }
    }
    def destroy = {
    }
}
