package denguemapmalaysia

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DengueController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Dengue.list(params), model:[dengueCount: Dengue.count()]
    }

    def show(Dengue dengue) {
        respond dengue
    }

    def create() {
        respond new Dengue(params)
    }

    @Transactional
    def save(Dengue dengue) {
        if (dengue == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dengue.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dengue.errors, view:'create'
            return
        }

        dengue.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dengue.label', default: 'Dengue'), dengue.id])
                redirect dengue
            }
            '*' { respond dengue, [status: CREATED] }
        }
    }

    def edit(Dengue dengue) {
        respond dengue
    }

    @Transactional
    def update(Dengue dengue) {
        if (dengue == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dengue.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dengue.errors, view:'edit'
            return
        }

        dengue.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dengue.label', default: 'Dengue'), dengue.id])
                redirect dengue
            }
            '*'{ respond dengue, [status: OK] }
        }
    }

    @Transactional
    def delete(Dengue dengue) {

        if (dengue == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dengue.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dengue.label', default: 'Dengue'), dengue.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'dengue.label', default: 'Dengue'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
