import { Request, Response } from "express";
import Usuario from "../models/user.model";

export const getUsers = async (req: Request, res: Response) => {
    const usuarios = await Usuario.findAll();
    res.json({usuarios});
}

export const getUser = async(req: Request, res: Response) => {
    const { id } = req.params;
    const usuario = await Usuario.findByPk(id);

    if (!usuario) {
        res.status(404).json({
            msg: `No existe usuario con id: ${id}`
        });
    }

    res.json(usuario);
}

export const postUser = async (req: Request, res: Response) => {
    
    const { nombre, email } = req.body;
    try {
        // existe email
        const existeEmail = Usuario.findOne({
            where: email
        });
        if (existeEmail) {
            return res.status(400).json({
                msg: `Email ${email} ya se encuentra registrado`
            });
        }
        
        const usuario = Usuario.build({ nombre, email });
        await usuario.save();
        res.json(usuario);

    } catch (error) {
        console.log(error);
        res.status(500).json({
            msg: 'Comuniquese con el administrador'
        });
    }
}

export const putUser = async (req: Request, res: Response) => {
    
    const { id } = req.params;
    const { nombre, email } = req.body;
    try {
        
        const usuario = await Usuario.findByPk(id);
        if (!usuario) {
            return res.status(404).json({
                msg: `No existe usuario  con id: ${id}`
            });
        }
        await usuario.update( {nombre} );

        res.json({
            usuario
        })

    } catch (error) {
        console.log(error);
        res.status(500).json({
            msg: 'Comuniquese con el administrador'
        });
    }

}

export const deleteUser = async (req: Request, res: Response) => {
    const { id } = req.params;
    const usuario = await Usuario.findByPk(id);
    if (!usuario) {
        return res.status(404).json({
            msg: `No existe usuario  con id: ${id}`
        });
    }

    // eliminación fisica
    // await usuario.destroy();

    // eliminación logica, estado 1 a 0
    await usuario.update({ estado: false });

    res.json({
        msg: 'Usuario eliminado'
    });
}