"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.deleteUser = exports.putUser = exports.postUser = exports.getUser = exports.getUsers = void 0;
const user_model_1 = __importDefault(require("../models/user.model"));
const getUsers = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const usuarios = yield user_model_1.default.findAll();
    res.json({ usuarios });
});
exports.getUsers = getUsers;
const getUser = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    const usuario = yield user_model_1.default.findByPk(id);
    if (!usuario) {
        res.status(404).json({
            msg: `No existe usuario con id: ${id}`
        });
    }
    res.json(usuario);
});
exports.getUser = getUser;
const postUser = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { nombre, email } = req.body;
    try {
        // existe email
        const existeEmail = user_model_1.default.findOne({
            where: email
        });
        if (existeEmail) {
            return res.status(400).json({
                msg: `Email ${email} ya se encuentra registrado`
            });
        }
        const usuario = user_model_1.default.build({ nombre, email });
        yield usuario.save();
        res.json(usuario);
    }
    catch (error) {
        console.log(error);
        res.status(500).json({
            msg: 'Comuniquese con el administrador'
        });
    }
});
exports.postUser = postUser;
const putUser = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    const { nombre, email } = req.body;
    try {
        const usuario = yield user_model_1.default.findByPk(id);
        if (!usuario) {
            return res.status(404).json({
                msg: `No existe usuario  con id: ${id}`
            });
        }
        yield usuario.update({ nombre });
        res.json({
            usuario
        });
    }
    catch (error) {
        console.log(error);
        res.status(500).json({
            msg: 'Comuniquese con el administrador'
        });
    }
});
exports.putUser = putUser;
const deleteUser = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    const usuario = yield user_model_1.default.findByPk(id);
    if (!usuario) {
        return res.status(404).json({
            msg: `No existe usuario  con id: ${id}`
        });
    }
    // eliminación fisica
    // await usuario.destroy();
    // eliminación logica, estado 1 a 0
    yield usuario.update({ estado: false });
    res.json({
        msg: 'Usuario eliminado'
    });
});
exports.deleteUser = deleteUser;
//# sourceMappingURL=user.controller.js.map