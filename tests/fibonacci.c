    @Override
    public Register visitVarExpr(VarExpr v) {
        assert v.type == v.vd.type;
        //System.out.println(" v.name: " + v.name + " v.type: " + v.type + " vd.type: " + v.vd.type);
        Register address = addressOf(v);
        return getValue(address, v.type);
    }

    @Override
    public Register visitArrayAccessExpr(ArrayAccessExpr aae) {
        Register address = addressOf(aae);
        return getValue(address, aae.type);
    }

    @Override
    public Register visitFieldAccessExpr(FieldAccessExpr fae) {
        assert fae.type != null;
        Register address = addressOf(fae);
        return getValue(address, fae.type);
    }

    @Override
    public Register visitValueAtExpr(ValueAtExpr vae) {
        assert vae.toDeref.type.isPointerType();
        Register address = addressOf(vae);
        return getValue(address, vae.type);
    }

    @Override
    public Register visitSizeOfExpr(SizeOfExpr soe) {
        Register val = regAllocater.get();
        writer.li(val, soe.toCheck.realSize());
        return val;
    }

    @Override
    public Register visitTypecastExpr(TypecastExpr te) {
        Register value  = te.fromExpr.accept(this);
        return value;
    }