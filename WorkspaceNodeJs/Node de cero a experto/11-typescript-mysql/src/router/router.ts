import { Router, Request, Response } from 'express';
import MySQL from '../mysql/mysql';


const router = Router();

router.get('/heroes', (req: Request, resp: Response) => {
    

    const query = `
        SELECT * FROM heroes`;

    MySQL.ejecutarQuery(query, (err: any, heroes: Object[]) =>{
    
        if(err){
            resp.status(400).json({
                ok: false,
                error: err
            });
        }

        resp.json({
            ok: true,
            heroes
        });
    });
});



router.get('/heroes/:id', (req: Request, resp: Response) => {

    const id = req.params.id;

    // scape id
    const scapeId = MySQL.instance.connection.escape(id);

    const query = `
        SELECT * FROM heroes WHERE id = ${scapeId}`;

    MySQL.ejecutarQuery(query, (err: any, heroe: Object[]) => {
        
        if(err){
            resp.status(400).json({
                ok: false,
                error: err
            });
        }

        resp.json({
            ok: true,
            heroe: heroe[0]
        });
    });

});


export default router;